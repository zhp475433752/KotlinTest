package com.zwwl.kotlintest.adaptation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.zwwl.kotlintest.R
import kotlinx.android.synthetic.main.activity_adaptation.*

/**
 * 适配测试
 */
class AdaptationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        CustomDensity.setCustomDensity(this, application)
        setContentView(R.layout.activity_adaptation)
        val tips = "设备：${application.resources.displayMetrics.widthPixels}_${application.resources.displayMetrics.heightPixels}"
        text_tips.text = tips
    }
}