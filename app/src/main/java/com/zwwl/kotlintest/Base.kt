package com.zwwl.kotlintest

/**
 * Created by zhanghuipeng on 2020/10/13.
 */
open class Base(val name: String) {

    init { println("Initializing Base") }

    open val size: Int =
        name.length.also { println("Initializing size in Base: $it") }
}