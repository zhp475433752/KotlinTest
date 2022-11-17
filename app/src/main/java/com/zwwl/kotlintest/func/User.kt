package com.zwwl.kotlintest.func

/**
 * Created by zhanghuipeng on 2022/11/17.
 */
class User(var name: String, var age: Int) {

    fun printMessage() {
        println("高阶函数 - 姓名：$name , 年龄：$age")
    }

}