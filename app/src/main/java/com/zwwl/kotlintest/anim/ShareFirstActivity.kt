package com.zwwl.kotlintest.anim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zwwl.kotlintest.databinding.ActivityAnimListBinding

import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.SharedElementCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zwwl.kotlintest.R
import kotlinx.android.synthetic.main.activity_anim_list.*


/**
 * 共享元素：实现从网格图片点击到viewpager的无缝过渡
 * 第一个页面的sharedElements和第二个页面的sharedElements的key保持一致
 */
class ShareFirstActivity : AppCompatActivity(), OnItemListener {

    private lateinit var binding: ActivityAnimListBinding
    private var bundle:Bundle?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list:MutableList<Int> = mutableListOf()
        list.add(R.drawable.animal_1)
        list.add(R.drawable.animal_2)
        list.add(R.drawable.animal_3)
        val adapter = AnimListAdapter(list)
        adapter.setOnClickListener(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        prepareTransitions()
    }

    override fun onActivityReenter(resultCode: Int, data: Intent) {
        super.onActivityReenter(resultCode, data)
        bundle = Bundle(data.extras)
    }

    /**
     * 重点方法
     */
    private fun prepareTransitions() {
        setExitSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(names: List<String>, sharedElements: MutableMap<String, View>) {
                    if (bundle != null) {
                        val position = bundle!!.getInt("index", 0)
                        val selectedViewHolder: RecyclerView.ViewHolder = recyclerView.findViewHolderForAdapterPosition(position) ?: return
                        sharedElements[position.toString()] = selectedViewHolder.itemView.findViewById(R.id.item_anim_image)
                        bundle = null
                    }
                }
            })
    }

    override fun onItemClick(imageView: ImageView, position: Int) {
        val intent = Intent(this, ShareSecondActivity::class.java)
        intent.putExtra("index", position)

        if (Build.VERSION.SDK_INT >= 22) {
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, position.toString())
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }


}