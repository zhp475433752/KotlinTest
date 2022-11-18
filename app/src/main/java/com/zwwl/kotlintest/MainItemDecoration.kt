package com.zwwl.kotlintest

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by zhanghuipeng on 2022/11/18.a
 */
class MainItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(margin, margin, margin, margin)
        val childLayoutPosition = parent.getChildLayoutPosition(view)
        val childCount = parent.childCount
        
    }

}