package com.zwwl.kotlintest.fullscreen

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.zwwl.kotlintest.R

/**
 * 全屏页面
 */
class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        testSystemUiVisibility()

        findViewById<Button>(R.id.full_screen_back_btn).setOnClickListener { finish() }
        mBaseFullscreen()

    }

    /**
     * 全屏显示，隐藏虚拟键和导航栏你
     */
    private fun mBaseFullscreen() {
//       全屏
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        /**
         * 水滴屏适配全面屏，这个代码生效
         * https://blog.51cto.com/u_14397532/5147342
         */
        if (Build.VERSION.SDK_INT >= 28) {
            val lp = window.attributes
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }
    }


    /**
     * SYSTEM_UI_FLAG_VISIBLE 默认显示
     * SYSTEM_UI_FLAG_LOW_PROFILE
    - SYSTEM_UI_FLAG_HIDE_NAVIGATION
    - SYSTEM_UI_FLAG_FULLSCREEN
    - SYSTEM_UI_FLAG_LAYOUT_STABLE
    - SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    - SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    - SYSTEM_UI_FLAG_IMMERSIVE
    - SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    ————————————————
    版权声明：本文为CSDN博主「QQxiaoqiang1573」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/QQxiaoqiang1573/article/details/79867127
     */
    private fun testSystemUiVisibility() {
        // 设置状态栏和导航栏中的图标变小，变模糊或者弱化其效果。这个标志一般用于游戏，电子书，视频，或者不需要去分散用户注意力的应用软件。
        // 测试结果：隐藏状态栏所有通知，除了时间和电量
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE

        // 隐藏导航栏，点击屏幕任意区域，导航栏将重新出现，并且不会自动消失。
        // 测试结果：隐藏屏幕底部的导航栏
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        // 隐藏状态栏，点击屏幕区域不会出现，需要从状态栏位置下拉才会出现。
        // 测试结果：隐藏状态栏所有的东西
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        // ----------------------------------后面这3个比较常用，一般用在沉浸式状态栏-------------------------------------

        // 将布局内容拓展到导航栏的后面。
        // 测试结果：屏幕底部的内容在底部导航栏后面显示
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // 将布局内容拓展到状态的后面
        // 测试结果：不设置的话，沉浸式会盖在状态栏上面。
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN


        // 稳定布局，主要是在全屏和非全屏切换时，布局不要有大的变化。同时，android:fitsSystemWindows 要设置为 true。
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        // 使状态栏和导航栏真正的进入沉浸模式,即全屏模式
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

        // 使状态栏和导航栏真正的进入沉浸模式,即全屏模式，和上面的类似，只不过手动滑动使状态和导航出来后，还有自动消失
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

    }
}