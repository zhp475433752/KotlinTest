package com.zwwl.kotlintest

/**
 * Created by zhanghuipeng on 2020/10/13.
 */
// 主构造函数
open class Person constructor(val name: String, val age:Int) {
    private val bar: Int = 1
    var testName = "testName"
    get() = "testName 被修改后的值"

    // 内部类 可以访问外部类成员 ，去掉 inner 就是 嵌套类，嵌套类不能访问外部类成员
    inner class ManMan {
        fun foo() = bar
    }

    // 次构造函数
    constructor(name: String) : this(name, 0) {}

    companion object Factory {
        fun create(): Person = Person("")
    }

    init {
        // 初始化块中的代码实际上会成为主构造函数的一部分
    }
}