package com.zwwl.kotlintest;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);
        for (int k = 0; k < list.size(); k++) {
            if (list.get(k) == 2) {
                list.remove(k);//通过下标移除元素，达不到预期效果，除非下标--
                k--;
            }
        }
        for (int m : list) {
            Log.e("Page2Activity","---m=----------"+m);
        }

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        for (int m : list2) {
            if (m == 2) {
                list2.remove(m);//直接在循环中移除元素会报异常
            }
            Log.e("Page2Activity","---m="+m);
        }
    }
}
