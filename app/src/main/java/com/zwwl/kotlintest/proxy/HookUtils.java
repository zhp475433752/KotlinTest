package com.zwwl.kotlintest.proxy;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhanghuipeng on 1/19/21.
 */
public class HookUtils {
    private Context context;

    public HookUtils(Context context) {
        this.context = context;
    }

    public void hookTestAPI29() {
        try {
            // 该HOOK以API 29 为例，其他版本请参考具体API
            // 第一步：获取 IActivityManagerSingleton
            Class<?> aClass = Class.forName("android.app.ActivityTaskManager");//类名需要根据API版本来确定
            Field declaredField = aClass.getDeclaredField("IActivityTaskManagerSingleton");
            declaredField.setAccessible(true);
            Object value = declaredField.get(null);

            Class<?> singletonClz = Class.forName("android.util.Singleton");
            Field instanceField = singletonClz.getDeclaredField("mInstance");
            instanceField.setAccessible(true);
            Object iActivityManagerObject = instanceField.get(value);

            // 第二步：获取我们的代理对象，这里因为 IActivityManager 是接口，我们使用动态代理的方式
            Class<?> iActivity = Class.forName("android.app.IActivityTaskManager");
            InvocationHandler handler = new StartActivityHandler(iActivityManagerObject);
            Object proxy = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{iActivity},
                    handler);

            // 第三步：偷梁换柱，将我们的 proxy 替换原来的对象
            instanceField.set(value, proxy);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("abc error: " + e.getMessage());
        }
    }

    class StartActivityHandler implements InvocationHandler {
        // 系统真正的对象
        private Object realIActivityManager;

        public StartActivityHandler(Object realIActivityManager) {
            this.realIActivityManager = realIActivityManager;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("abc : --------------------- invoke ---------------------" + method.getName());
            if ("startActivity".equals(method.getName())) {
                System.out.println("abc : --------------------- startActivity ---------------------");
                Intent intent = null;
                int index = -1;
                for (int i = 0; i < args.length; i++) {
                    Object obj = args[i];
                    if (obj instanceof Intent) {
                        //找到 startActivity 传递进来的真实 Intent
                        intent = (Intent) obj;
                        index = i;
                        break;
                    }
                }

                // 构造假的意图，将真正的意图进行篡改
                Intent intentFade = new Intent(context, ProxyActivity.class);
                //我们将真实的意图封装在假意图当中
                intentFade.putExtra("originIntent", intent);
                if (index != -1) {
                    args[index] = intentFade;
                }
            }
            return method.invoke(realIActivityManager, args);
        }
    }

}
