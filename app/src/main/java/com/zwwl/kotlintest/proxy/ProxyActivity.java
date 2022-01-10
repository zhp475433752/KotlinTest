package com.zwwl.kotlintest.proxy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zwwl.kotlintest.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 代理Activity
 */
public class ProxyActivity extends AppCompatActivity{
    //logt快捷键生成TAG
    private static final String TAG = "ProxyActivity";
    private Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);

        button1 = findViewById(R.id.testView);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProxyActivity.this, "我是button1", Toast.LENGTH_LONG).show();
            }
        });
        button2 = findViewById(R.id.btnHook);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProxyActivity.this, "我是button2222", Toast.LENGTH_LONG).show();
            }
        });

        try {
            hookOnClickListener(button2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Toast.makeText(this, "我是代理页面哈哈哈", Toast.LENGTH_LONG).show();
    }


    public void hookOnClickListener(View view) throws Exception {
        // 第一步：反射得到 ListenerInfo 对象
        Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
        getListenerInfo.setAccessible(true);
        Object listenerInfo = getListenerInfo.invoke(view);
        // 第二步：得到原始的 OnClickListener事件方法
        Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
        Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
        mOnClickListener.setAccessible(true);
        View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);
        // 第三步：用Hook代理类 替换原始的 OnClickListener
        View.OnClickListener hookedOnClickListener = new ViewClickListenerProxy(originOnClickListener);
        mOnClickListener.set(listenerInfo, hookedOnClickListener);
    }
}
