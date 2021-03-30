package com.zwwl.kotlintest.ref;

import android.util.Log;

/**
 * Created by zhanghuipeng on 3/19/21.
 */
public enum TestEnum implements d{
    INSTANCE;

    @Override
    public void a(int i2, Object... objArr) {
        Log.d("###########", "我是TestEnum的实现方法a-------");
    }
}
