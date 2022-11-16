package com.zwwl.kotlintest.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.zwwl.kotlintest.R
import kotlinx.android.synthetic.main.activity_flow_test.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FlowTestActivity : AppCompatActivity() {
    private val viewModel by viewModels<FlowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_test)
        flow_btn.setOnClickListener {
            lifecycleScope.launch {
                // collectLatest函数只接收处理最新的数据。
                // 如果有新数据到来了而前一个数据还没有处理完，则会将前一个数据剩余的处理逻辑全部取消。
                viewModel.timeFlow.collectLatest(){
                    flow_text.text = it.toString()
                    delay(3000)
                }
            }
        }
    }
}