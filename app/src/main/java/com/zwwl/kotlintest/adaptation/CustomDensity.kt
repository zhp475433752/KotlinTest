package com.zwwl.kotlintest.adaptation

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration

/**
 * Created by zhanghuipeng on 2022/6/17.
 */
object CustomDensity {
    var sNonCompatDensity = 0f
    var sNonCompatScaleDensity = 0f

    fun setCustomDensity(activity: Activity, application: Application) {
        val appDisplayMetrics = application.resources.displayMetrics
        if (sNonCompatDensity == 0f) {
            sNonCompatDensity = appDisplayMetrics.density
            sNonCompatScaleDensity = appDisplayMetrics.scaledDensity
            application.registerComponentCallbacks(object : ComponentCallbacks{
                override fun onConfigurationChanged(newConfig: Configuration) {
                    if (newConfig.fontScale > 0) {
                        sNonCompatScaleDensity = application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {

                }

            })
        }

        val targetDensity: Float = appDisplayMetrics.widthPixels / 960f
        val targetScaleDensity: Float = targetDensity * (sNonCompatScaleDensity / sNonCompatDensity)
        val targetDensityDpi: Int = (160 * targetDensity).toInt()

        appDisplayMetrics.density = targetDensity
        appDisplayMetrics.scaledDensity = targetScaleDensity
        appDisplayMetrics.densityDpi = targetDensityDpi

        val displayMetrics = activity.resources.displayMetrics
        displayMetrics.density = targetDensity
        displayMetrics.scaledDensity = targetScaleDensity
        displayMetrics.densityDpi = targetDensityDpi


    }
}