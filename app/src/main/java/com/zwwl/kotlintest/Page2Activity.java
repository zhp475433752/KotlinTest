package com.zwwl.kotlintest;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import androidx.appcompat.app.AppCompatActivity;

public class Page2Activity extends AppCompatActivity {
    Surface surface;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    SurfaceTexture surfaceTexture;

    TextureView textureView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        String t1 = "用户协议,隐私政策";
        String[] split = t1.split(",");
        System.out.println("Page2Activity==="+split.length);
    }
}
