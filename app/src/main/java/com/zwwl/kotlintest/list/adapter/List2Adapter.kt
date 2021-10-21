package com.zwwl.kotlintest.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zwwl.kotlintest.R
import com.zwwl.kotlintest.Utils
import com.zwwl.kotlintest.list.bean.ItemEntity
import com.zwwl.kotlintest.list.bean.ItemType

/**
 * Created by zhanghuipeng on 2021/10/19.
 */
class List2Adapter(private val data: List<ItemEntity>) :
    RecyclerView.Adapter<List2Adapter.List2ViewHolder>() {

    private val map: MutableMap<String, Boolean> = mutableMapOf()

    private val TAG = "List2Adapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): List2ViewHolder {
        return List2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list2_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: List2ViewHolder, position: Int) {
        val itemEntity = data[position]
        val marginLeft = Utils.dip2px(holder.root.context, 15f)
        val marginRight = marginLeft
        var marginTop = 0
        val marginBottom = 0
        when (itemEntity.itemType) {
            ItemType.TOP -> {
                holder.root.setBackgroundResource(R.drawable.bg_item_top)
                marginTop = marginLeft
                holder.divider.visibility = View.VISIBLE
                holder.bottom.visibility = View.GONE
            }
            ItemType.CENTER -> {
                holder.root.setBackgroundResource(R.drawable.bg_item_center)
                marginTop = 0
                holder.divider.visibility = View.VISIBLE
                holder.bottom.visibility = View.GONE
            }
            ItemType.BOTTOM -> {
                holder.root.setBackgroundResource(R.drawable.bg_item_bottom)
                marginTop = 0
                holder.divider.visibility = View.GONE
                if (position == data.size - 1) {
                    holder.bottom.visibility = View.VISIBLE
                } else {
                    holder.bottom.visibility = View.GONE
                }
            }
            ItemType.ONE -> {
                holder.root.setBackgroundResource(R.drawable.bg_item_one)
                marginTop = marginLeft
                holder.divider.visibility = View.GONE
                if (position == data.size - 1) {
                    holder.bottom.visibility = View.VISIBLE
                } else {
                    holder.bottom.visibility = View.GONE
                }
            }
        }
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            Utils.dip2px(holder.root.context, 55f)
        )
        layoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom)
        holder.root.layoutParams = layoutParams

        //设置数据
        holder.icon.setImageResource(itemEntity.resIcon)
        holder.label.text = itemEntity.label
        holder.tips.text = itemEntity.tips
        holder.root.setOnClickListener { itemEntity.action.apply() }

    }

    override fun getItemCount(): Int = data.size

    inner class List2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val root: LinearLayout = itemView.findViewById(R.id.item_list2_container)
        val divider: View = itemView.findViewById(R.id.item_list2_divider)
        val bottom: View = itemView.findViewById(R.id.item_list2_bottom)
        val icon: ImageView = itemView.findViewById(R.id.item_list2_icon)
        val label: TextView = itemView.findViewById(R.id.item_list2_label)
        val tips: TextView = itemView.findViewById(R.id.item_list2_tips)
    }

}