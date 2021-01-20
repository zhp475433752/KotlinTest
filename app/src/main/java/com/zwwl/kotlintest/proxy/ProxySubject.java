package com.zwwl.kotlintest.proxy;

/**
 * Created by zhanghuipeng on 1/18/21.
 */
public class ProxySubject implements ISubject {
    ISubject mRealSubject;

    public ProxySubject(ISubject mRealSubject) {
        super();
        this.mRealSubject = mRealSubject;
    }

    @Override
    public void doAction(String action) {
        preRequest();
        mRealSubject.doAction(action);
        postRequest();
    }

    protected void postRequest() {
        System.out.println("postRequest");

    }

    protected void preRequest() {
        System.out.println("preRequest");

    }
}
