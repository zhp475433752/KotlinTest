package com.zwwl.kotlintest.proxy;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhanghuipeng on 1/29/21.
 */
class ToastHandler implements InvocationHandler {
    private Context context;
    // 系统真正的对象
    private Object realIActivityManager;

    public ToastHandler(Context context, Object realIActivityManager) {
        this.context = context;
        this.realIActivityManager = realIActivityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("abc : --------------------- invoke ---------------------" + method.getName());
        if ("show".equals(method.getName())) {
            System.out.println("abc : --------------------- show ---------------------");

//            Intent intent = null;
//            int index = -1;
//            for (int i = 0; i < args.length; i++) {
//                Object obj = args[i];
//                if (obj instanceof Intent) {
//                    //找到 startActivity 传递进来的真实 Intent
//                    intent = (Intent) obj;
//                    index = i;
//                    break;
//                }
//            }
//
//            // 构造假的意图，将真正的意图进行篡改
//            Intent intentFade = new Intent(context, ProxyActivity.class);
//            //我们将真实的意图封装在假意图当中
//            intentFade.putExtra("originIntent", intent);
//            if (index != -1) {
//                args[index] = intentFade;
//            }
        }
        return method.invoke(realIActivityManager, args);
    }
}
