package com.zwwl.kotlintest.flow

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zwwl.kotlintest.R
import kotlinx.android.synthetic.main.activity_flow_test.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * 测试 flow
 * 测试 SystemUiVisibility
 */
class FlowTestActivity : AppCompatActivity() {
    private val viewModel by viewModels<FlowViewModel>()
    private var job: Job ? = null

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_test)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        testSystemUiVisibility()

        // 关闭协程
        flow_btn_close.setOnClickListener {
            job?.cancel()
            flow_text.text = "倒计时协程被取消"
        }

        // 开始协程
        flow_btn.setOnClickListener {
            job = lifecycleScope.launch {
                // collectLatest函数只接收处理最新的数据。
                // 如果有新数据到来了而前一个数据还没有处理完，则会将前一个数据剩余的处理逻辑全部取消。
                viewModel.timeFlow.collectLatest(){
                    flow_text.text = it.toString()
                    delay(3000)
                }
            }


            // flow 顺序发送多个值
            lifecycleScope.launch {
                // 挂起运算符 collect 会阻塞后面的代码，直到 flow 执行结束
                // 每次调用 collect 时，flow lambda 都会从头开始执行。
                makeFlow().collect { value ->
                    // 顺序接收到 flow 发送的多个值
                    println("got $value")
                    flow_receive.text = flow_receive.text.toString().plus(value)
                }
                println("flow is completed")

            }
        }
    }

    /**
     * SYSTEM_UI_FLAG_LOW_PROFILE
    - SYSTEM_UI_FLAG_HIDE_NAVIGATION
    - SYSTEM_UI_FLAG_FULLSCREEN
    - SYSTEM_UI_FLAG_LAYOUT_STABLE
    - SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    - SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    - SYSTEM_UI_FLAG_IMMERSIVE
    - SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    ————————————————
    版权声明：本文为CSDN博主「QQxiaoqiang1573」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/QQxiaoqiang1573/article/details/79867127
     */
    private fun testSystemUiVisibility() {
        // 设置状态栏和导航栏中的图标变小，变模糊或者弱化其效果。这个标志一般用于游戏，电子书，视频，或者不需要去分散用户注意力的应用软件。
        // 测试结果：隐藏状态栏所有通知，除了时间和电量
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE

        // 隐藏导航栏，点击屏幕任意区域，导航栏将重新出现，并且不会自动消失。
        // 测试结果：隐藏屏幕底部的导航栏
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        // 隐藏状态栏，点击屏幕区域不会出现，需要从状态栏位置下拉才会出现。
        // 测试结果：隐藏状态栏所有的东西
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        // ----------------------------------后面这3个比较常用，一般用在沉浸式状态栏-------------------------------------

        // 将布局内容拓展到导航栏的后面。
        // 测试结果：屏幕底部的内容在底部导航栏后面显示
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // 将布局内容拓展到状态的后面
        // 测试结果：不设置的话，沉浸式会盖在状态栏上面。
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN


        // 稳定布局，主要是在全屏和非全屏切换时，布局不要有大的变化。同时，android:fitsSystemWindows 要设置为 true。
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        // 使状态栏和导航栏真正的进入沉浸模式,即全屏模式
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

        // 使状态栏和导航栏真正的进入沉浸模式,即全屏模式，和上面的类似，只不过手动滑动使状态和导航出来后，还有自动消失
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

    }

    private fun makeFlow() = flow {
        println("sending first value")
        emit("发送1 - ")
        delay(1000)
        println("first value collected, sending another value")
        emit("发送2 - ")
        delay(1000)
        println("second value collected, sending a third value")
        emit("发送3 - ")
        delay(1000)
        println("done")
    }
}