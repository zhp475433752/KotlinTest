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