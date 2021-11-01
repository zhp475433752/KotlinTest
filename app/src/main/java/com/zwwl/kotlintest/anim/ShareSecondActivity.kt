package com.zwwl.kotlintest.anim

import android.annotation.TargetApi
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.zwwl.kotlintest.R
import kotlinx.android.synthetic.main.activity_share.*
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.mutableListOf
import kotlin.collections.set

class ShareSecondActivity : AppCompatActivity(){
    var pagerAdapter:PagerAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        val index = intent.getIntExtra("index", 0)
        val list:MutableList<Int> = mutableListOf()
        list.add(R.drawable.animal_1)
        list.add(R.drawable.animal_2)
        list.add(R.drawable.animal_3)
        pagerAdapter = PagerAdapter(list, supportFragmentManager)
        viewPagerShare.adapter = pagerAdapter
        viewPagerShare.currentItem = index

        prepareSharedElementTransition()
    }

    override fun supportFinishAfterTransition() {
        val data = Intent()
        data.putExtra("index", viewPagerShare.currentItem)
        setResult(RESULT_OK, data)
        super.supportFinishAfterTransition()
    }

    override fun onBackPressed() {
        val data = Intent()
        data.putExtra("index", viewPagerShare.currentItem)
        setResult(RESULT_OK, data)
        super.supportFinishAfterTransition()
    }

    /**
     * 重点方法
     */
    private fun prepareSharedElementTransition() {
        setEnterSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(names: List<String>, sharedElements: MutableMap<String, View>) {
                    val fragment: ShareFragment = pagerAdapter?.instantiateItem(
                        viewPagerShare,
                        viewPagerShare.currentItem) as ShareFragment
                    sharedElements[viewPagerShare?.currentItem.toString()] = fragment.getShareElement()
                }
            })
    }

    inner class PagerAdapter(val list:List<Int>, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return ShareFragment.newInstance(list[position])
        }

        override fun getCount(): Int {
            return list.size
        }
    }

}