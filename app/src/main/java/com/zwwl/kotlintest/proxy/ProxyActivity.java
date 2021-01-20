package com.zwwl.kotlintest.proxy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zwwl.kotlintest.R;

/**
 * 代理Activity
 */
public class ProxyActivity extends AppCompatActivity {
    //logt快捷键生成TAG
    private static final String TAG = "ProxyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
    }
}
