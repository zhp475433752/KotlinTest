package com.zwwl.kotlintest.anim

import android.widget.ImageView

/**
 * Created by zhanghuipeng on 2021/11/1.
 */
interface OnItemListener {
    fun onItemClick(imageView:ImageView, position:Int)
}