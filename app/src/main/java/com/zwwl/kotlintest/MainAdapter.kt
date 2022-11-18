package com.zwwl.kotlintest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by zhanghuipeng on 2022/11/18.
 */
class MainAdapter : ListAdapter<MainBean, MainAdapter.MainViewHolder>(MainDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_main, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val mainBean = getItem(position)
        holder.tv?.text = mainBean.title
        holder.itemView.setOnClickListener { mainBean.func.invoke() }
    }

    class MainViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv:TextView? = null
        init {
            tv = view.findViewById(R.id.item_list_main_tv)
        }
//        val tv: TextView = view.findViewById(R.id.item_list_main_tv)
    }

}

private class MainDiffCallback: DiffUtil.ItemCallback<MainBean>() {
    override fun areItemsTheSame(oldItem: MainBean, newItem: MainBean): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MainBean, newItem: MainBean): Boolean {
        return oldItem == newItem
    }

}