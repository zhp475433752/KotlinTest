package com.zwwl.kotlintest.anim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat.startPostponedEnterTransition
import androidx.recyclerview.widget.RecyclerView
import com.zwwl.kotlintest.R

/**
 * Created by zhanghuipeng on 2021/11/1.
 * 无用
 */
class ShareAdapter(val list:List<Int>): RecyclerView.Adapter<ShareAdapter.ShareViewHolder>() {

    private var onClickListener:View.OnClickListener? = null

    fun setOnClickListener(onClickListener: View.OnClickListener) {
        this.onClickListener = onClickListener
    }

    inner class ShareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.item_share_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShareViewHolder {
        return ShareViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_share_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ShareViewHolder, position: Int) {
        holder.imageView.setImageResource(list[position])
        holder.imageView.setOnClickListener {
            onClickListener?.onClick(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}