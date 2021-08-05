package com.zwwl.kotlintest;

import android.app.Application;

import androidx.multidex.MultiDex;

/**
 * Created by zhanghuipeng on 1/19/21.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
