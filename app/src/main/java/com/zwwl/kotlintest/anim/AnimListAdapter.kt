package com.zwwl.kotlintest.anim

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.zwwl.kotlintest.R

/**
 * Created by zhanghuipeng on 2021/11/1.
 */
class AnimListAdapter(val list:List<Int>):RecyclerView.Adapter<AnimListAdapter.AnimListViewHolder>() {
    private var onClickListener:OnItemListener? = null
    fun setOnClickListener(onClickListener: OnItemListener) {
        this.onClickListener = onClickListener
    }
    inner class AnimListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.item_anim_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimListViewHolder {
        return AnimListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_anim_layout, parent, false))
    }

    override fun onBindViewHolder(holder: AnimListViewHolder, position: Int) {
        holder.image.setImageResource(list[position])
        holder.itemView.setOnClickListener {
            onClickListener?.onItemClick(holder.image, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}