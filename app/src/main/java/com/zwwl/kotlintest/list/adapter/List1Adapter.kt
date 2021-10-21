package com.zwwl.kotlintest.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.zwwl.kotlintest.R
import com.zwwl.kotlintest.list.bean.GroupEntity

/**
 * Created by zhanghuipeng on 2021/10/19.
 */
class List1Adapter(private val data: List<GroupEntity>) :
    RecyclerView.Adapter<List1Adapter.List1ViewHolder>() {

    private val TAG = "List1Adapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): List1ViewHolder {
        return List1ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list1_parent, parent, false)
        )
    }

    override fun onBindViewHolder(holder: List1ViewHolder, position: Int) {
        if (holder.root.childCount != 0) {
            Log.d(TAG,"----------holder.root.childCount 复用 -----position-----$position")
            holder.root.removeAllViews()
        } else {
            Log.d(TAG,"----------holder.root.childCount == 0 ------position----$position")
        }
        val subList = data[position].list
        if (subList != null && subList.size != 0) {
            for (subItem in subList) {
                val view = LayoutInflater.from(holder.root.context).inflate(R.layout.item_list1_layout, holder.root, false)
                val icon = view.findViewById<ImageView>(R.id.item_list1_icon)
                val label = view.findViewById<TextView>(R.id.item_list1_label)
                val tips = view.findViewById<TextView>(R.id.item_list1_tips)
                val divider = view.findViewById<View>(R.id.item_list1_divider)
                if (subList.indexOf(subItem) == subList.size - 1) {
                    divider.visibility = View.GONE
                } else {
                    divider.visibility = View.VISIBLE
                }
                icon.setImageResource(subItem.resIcon)
                label.text = subItem.label
                tips.text = subItem.tips
                view.setOnClickListener { subItem.action.apply() }
                holder.root.addView(view)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    inner class List1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val root:LinearLayout = itemView.findViewById(R.id.item_list_root)
    }

}