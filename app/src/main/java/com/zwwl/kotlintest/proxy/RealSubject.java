package com.zwwl.kotlintest.proxy;

/**
 * Created by zhanghuipeng on 1/18/21.
 */
public class RealSubject implements ISubject {
    @Override
    public void doAction(String action) {
        System.out.println("I am RealSubject, do action "+ action);
    }
}
