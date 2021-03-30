package com.zwwl.kotlintest;

import android.os.RemoteException;

/**
 * Created by zhanghuipeng on 2/20/21.
 */
class AppManagerBinder extends IAppManager.Stub{
    @Override
    public String getName() throws RemoteException {
        String name = "我是姓名哈哈哈哈";
        return name;
    }
}
