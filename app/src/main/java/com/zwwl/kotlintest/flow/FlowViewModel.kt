package com.zwwl.kotlintest.flow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow

/**
 * Created by zhanghuipeng on 2022/11/15.
 */
class FlowViewModel : ViewModel() {
    val timeFlow = flow {
        var time = 0
        while (true) {
            emit(time)
            kotlinx.coroutines.delay(1000)
            time++
        }
    }
}