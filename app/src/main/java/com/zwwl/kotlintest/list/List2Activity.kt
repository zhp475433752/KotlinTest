package com.zwwl.kotlintest.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zwwl.kotlintest.R
import com.zwwl.kotlintest.list.adapter.List2Adapter
import com.zwwl.kotlintest.list.bean.ItemEntity
import com.zwwl.kotlintest.list.bean.ItemType
import kotlinx.android.synthetic.main.activity_list2.*

/**
 * 适合大量数据的分组方案
 */
class List2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list2)
        list2_recyclerview.layoutManager = LinearLayoutManager(this)
        val data: MutableList<ItemEntity> = mutableListOf()

        val entity0 = ItemEntity(ItemType.TOP, R.drawable.ic_baseline_wifi_24, "WLAN", "doushen-dev", {})
        val entity1 = ItemEntity(ItemType.CENTER, R.drawable.ic_baseline_desktop_mac_24, "桌面", "默认桌面", {})
        val entity2 = ItemEntity( ItemType.CENTER, R.drawable.ic_baseline_image_24, "图片浏览", "", {})
        val entity3 = ItemEntity(ItemType.BOTTOM, R.drawable.ic_baseline_sd_card_24, "外部存储", "", {})

        val entity4 = ItemEntity(ItemType.ONE, R.drawable.ic_baseline_search_24, "搜索", "", {})

        val entity5 = ItemEntity(ItemType.TOP, R.drawable.ic_baseline_send_24, "发送邮件", "", {})
        val entity6 = ItemEntity(ItemType.BOTTOM, R.drawable.ic_baseline_settings_24, "设置中心", "", {})

        val entity7 = ItemEntity(ItemType.TOP, R.drawable.ic_baseline_sports_esports_24, "球类运动", "", {})
        val entity8 = ItemEntity(ItemType.CENTER, R.drawable.ic_baseline_sports_basketball_24, "篮球", "NBA", {})
        val entity9 = ItemEntity( ItemType.CENTER, R.drawable.ic_baseline_star_rate_24, "五角星", "", {})
        val entity10 = ItemEntity( ItemType.CENTER, R.drawable.ic_baseline_support_24, "支持", "", {})
        val entity11 = ItemEntity( ItemType.CENTER, R.drawable.ic_baseline_sync_24, "同步", "", {})
        val entity12 = ItemEntity(ItemType.BOTTOM, R.drawable.ic_baseline_touch_app_24, "触摸反馈", "", {})

        val entity13 = ItemEntity(ItemType.TOP, R.drawable.ic_baseline_videocam_24, "视频", "", {})
        val entity14 = ItemEntity( ItemType.CENTER, R.drawable.ic_baseline_wb_sunny_24, "太阳☀️", "", {})
        val entity15 = ItemEntity(ItemType.BOTTOM, R.drawable.ic_baseline_wc_24, "洗手间🚾", "", {})

        val entity16 = ItemEntity(ItemType.ONE, R.drawable.ic_baseline_bluetooth_24, "蓝牙", "已关闭", {})

        data.add(entity0)
        data.add(entity1)
        data.add(entity2)
        data.add(entity3)
        data.add(entity4)
        data.add(entity5)
        data.add(entity6)
        data.add(entity7)
        data.add(entity8)
        data.add(entity9)
        data.add(entity10)
        data.add(entity11)
        data.add(entity12)
        data.add(entity13)
        data.add(entity14)
        data.add(entity15)
        data.add(entity16)

        data.add(entity0)
        data.add(entity1)
        data.add(entity2)
        data.add(entity3)
        data.add(entity4)
        data.add(entity5)
        data.add(entity6)
        data.add(entity7)
        data.add(entity8)
        data.add(entity9)
        data.add(entity10)
        data.add(entity11)
        data.add(entity12)
        data.add(entity13)
        data.add(entity14)
        data.add(entity15)
        data.add(entity16)
        list2_recyclerview.adapter = List2Adapter(data)

    }
}