package com.zwwl.kotlintest.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zwwl.kotlintest.R
import com.zwwl.kotlintest.list.adapter.List1Adapter
import com.zwwl.kotlintest.list.adapter.MyItemDecoration
import com.zwwl.kotlintest.list.bean.GroupEntity
import com.zwwl.kotlintest.list.bean.SubEntity
import kotlinx.android.synthetic.main.activity_list1.*

/**
 * 列表item为线性布局，动态添加子item
 * 不适合大量的数据
 */
class List1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list1)
        list1_recyclerview.layoutManager = LinearLayoutManager(this)
        val data: MutableList<GroupEntity> = mutableListOf()
        val groupNet = GroupEntity("网络相关", mutableListOf())
        groupNet.list.add(SubEntity(R.drawable.ic_baseline_wifi_24, "WLAN", "doushen-dev", {}))
        groupNet.list.add(SubEntity(R.drawable.ic_baseline_bluetooth_24, "蓝牙", "已关闭", {}))
        groupNet.list.add(SubEntity(R.drawable.ic_baseline_system_update_24, "移动网络", "", {}))
        groupNet.list.add(SubEntity(R.drawable.ic_baseline_support_24, "超级终端", "", {}))

        val groupDesk = GroupEntity("桌面", mutableListOf())
        groupDesk.list.add(SubEntity(R.drawable.ic_baseline_image_24, "桌面和壁纸", "", {}))
        groupDesk.list.add(SubEntity(R.drawable.ic_baseline_sd_card_24, "显示和亮度", "", {}))

        val groupSports = GroupEntity("运动", mutableListOf())
        groupSports.list.add(SubEntity(R.drawable.ic_baseline_sports_basketball_24, "篮球🏀", "", {}))
        groupSports.list.add(SubEntity(R.drawable.ic_baseline_sports_esports_24, "游戏", "", {}))
        groupSports.list.add(SubEntity(R.drawable.ic_baseline_star_rate_24, "五角星", "", {}))
        groupSports.list.add(SubEntity(R.drawable.ic_baseline_support_24, "支持", "", {}))
        groupSports.list.add(SubEntity(R.drawable.ic_baseline_sync_24, "同步", "", {}))
        groupSports.list.add(SubEntity(R.drawable.ic_baseline_touch_app_24, "触摸反馈", "", {}))
        groupSports.list.add(SubEntity(R.drawable.ic_baseline_videocam_24, "开始录屏", "", {}))
        groupSports.list.add(SubEntity(R.drawable.ic_baseline_wb_sunny_24, "太阳", "", {}))
        groupSports.list.add(SubEntity(R.drawable.ic_baseline_wc_24, "洗手间", "", {}))


        val groupSound = GroupEntity("sound", mutableListOf())
        groupSound.list.add(SubEntity(R.drawable.ic_baseline_search_24, "搜索", "", {}))
        groupSound.list.add(SubEntity(R.drawable.ic_baseline_send_24, "发送邮件", "", {}))
        groupSound.list.add(SubEntity(R.drawable.ic_notifications_black_24dp, "通知", "", {}))
        groupSound.list.add(SubEntity(R.drawable.ic_baseline_settings_24, "设置", "", {}))

        data.add(groupNet)
        data.add(groupDesk)
        data.add(groupSports)
        data.add(groupSound)
        data.add(groupNet)
        data.add(groupDesk)
        data.add(groupSports)
        data.add(groupSound)
        data.add(groupNet)
        data.add(groupDesk)
        data.add(groupSports)
        data.add(groupSound)
        list1_recyclerview.addItemDecoration(MyItemDecoration())
        list1_recyclerview.adapter = List1Adapter(data)
    }
}