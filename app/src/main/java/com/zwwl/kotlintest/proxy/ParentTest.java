package com.zwwl.kotlintest.proxy;

/**
 * Created by zhanghuipeng on 1/29/21.
 */
class ParentTest {
    private int code = 1;

    class SonTest {
        public int getResult(int id, String msg) {
            return id+1;
        }
    }
}
