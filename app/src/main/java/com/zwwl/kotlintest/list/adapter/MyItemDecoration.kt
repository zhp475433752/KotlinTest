package com.zwwl.kotlintest.list.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Created by zhanghuipeng on 2021/10/19.
 */
class MyItemDecoration: ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view)== 0) {
            outRect.set(50, 50, 50, 50)
        } else {
            outRect.set(50, 0, 50, 50)
        }

    }

}