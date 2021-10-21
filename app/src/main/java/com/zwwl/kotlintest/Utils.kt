package com.zwwl.kotlintest

import android.content.Context

/**
 * Created by zhanghuipeng on 2020/10/13.
 */
object Utils {
    val ID_NAME = "ZHANG"

    fun dip2px(context: Context, dp: Float): Int {
        val density = context.resources.displayMetrics.density
        return (density * dp + 0.5f).toInt()
    }
}