package com.zwwl.kotlintest

/**
 * Created by zhanghuipeng on 12/12/20.
 */
class Human(var gender: Gender) {
    var name = ""
    set(value) {
        field = when (gender) {
            Gender.MALE -> "这是男人-$value"
            Gender.FEMALE -> "这是女人-$value"
            else -> "性别不详"
        }
    }
}