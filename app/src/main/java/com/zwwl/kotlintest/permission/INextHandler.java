package com.zwwl.kotlintest.permission;

/**
 * Created by baiwenzhi on 2018/8/16.
 */

public interface INextHandler {

    /**
     * 同意操作下一步
     */
    void agreeHandlerNext();

    /**
     * 拒绝操作下一步
     */
    void refuseHandlerNext();


}
