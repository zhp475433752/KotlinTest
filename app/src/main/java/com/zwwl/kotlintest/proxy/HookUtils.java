package com.zwwl.kotlintest.proxy;

import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
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
            Object value = declaredField.get(null);//ActivityTaskManager

            Class<?> singletonClz = Class.forName("android.util.Singleton");
            Field instanceField = singletonClz.getDeclaredField("mInstance");
            instanceField.setAccessible(true);
            Object iActivityManagerObject = instanceField.get(value);//IActivityTaskManager最终执行startActivity

            // 第二步：获取我们的代理对象，这里因为 IActivityTaskManager 是接口，我们使用动态代理的方式
            Class<?> iActivity = Class.forName("android.app.IActivityTaskManager");
            InvocationHandler handler = new StartActivityHandler(context, iActivityManagerObject);
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

    public void hookTestAPI26() {
        try {
            // 该HOOK以API 26 为例，其他版本请参考具体API
            // 第一步：获取 IActivityManagerSingleton
            Class<?> aClass = Class.forName("android.app.ActivityManager");//类名需要根据API版本来确定
            Field declaredField = aClass.getDeclaredField("IActivityManagerSingleton");
            declaredField.setAccessible(true);
            Object value = declaredField.get(null);//ActivityTaskManager

            Class<?> singletonClz = Class.forName("android.util.Singleton");
            Field instanceField = singletonClz.getDeclaredField("mInstance");
            instanceField.setAccessible(true);
            Object iActivityManagerObject = instanceField.get(value);//IActivityTaskManager最终执行startActivity

            // 第二步：获取我们的代理对象，这里因为 IActivityTaskManager 是接口，我们使用动态代理的方式
            Class<?> iActivity = Class.forName("android.app.IActivityManager");
            InvocationHandler handler = new StartActivityHandler(context, iActivityManagerObject);
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

}
