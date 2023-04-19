package com.zwwl.kotlintest.adaptation

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
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

        val metrices = resources.displayMetrics
        val dpi = metrices.densityDpi
        val density = metrices.density
        val width = metrices.widthPixels.toFloat()
        val height = metrices.heightPixels.toFloat()

        Log.i("dpi==>", dpi.toString() + "")
        Log.i("density==>", density.toString() + "")
        Log.i("xdpi==>", metrices.xdpi.toString() + "")
        Log.i("ydpi==>", metrices.ydpi.toString() + "")
        Log.i("width==>", width.toString() + "")
        Log.i("height==>", height.toString() + "")
    }
}