package com.zwwl.kotlintest.thread;

import android.util.Log;

/**
 * Created by zhanghuipeng on 6/2/21.
 */
public class MyThread extends Thread {
    private static final String TAG = "MainActivity";
    public void run(){
        super.run();
        try {
            for(int i=0; i<10; i++){
                if(this.isInterrupted()) {
                    Log.d(TAG, "线程已经终止， for循环不再执行");
                    throw new InterruptedException();
                }
                Log.d(TAG, "i="+(i+1));
                Thread.sleep(100);
            }
            Log.d(TAG, "这是for循环外面的语句，只有抛出异常才不也会被执行，否则线程终端后还是会执行的！");
        } catch (InterruptedException e) {
            Log.d(TAG, "进入MyThread.java类中的catch了。。。");
            e.printStackTrace();
        }
    }
}
