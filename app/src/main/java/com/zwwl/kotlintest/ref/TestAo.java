package com.zwwl.kotlintest.ref;

import android.util.Log;

/**
 * Created by zhanghuipeng on 3/15/21.
 */
public class TestAo implements d{
    public final Object get(int i2, Object obj) {
        return (Object)3;
    }

    public String getTestAo(String key, Object... args) {
        return "我是getTestAohhaahffefhwefhwefwe";
    }

    @Override
    public void a(int i2, Object... objArr) {
        Log.d("###########", "我是d的实现方法a-------");
    }
}
