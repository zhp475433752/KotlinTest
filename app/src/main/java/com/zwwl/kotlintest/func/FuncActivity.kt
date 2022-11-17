package com.zwwl.kotlintest.func

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zwwl.kotlintest.R

/**
 * 高阶函数测试
 */
class FuncActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_func)
        testFunc1()
        testFunc2()
        testFunc3()
        testFunc4()
    }

    private fun testFunc1() {
        val u1 = fun() = println("高阶函数 - 要准备打印个人信息了")
        val u2 = fun():User  = User("hpzhang", 30)
        func1(u1, u2)
        println("高阶函数 - -----------------------------------------------")
    }

    private fun testFunc2() {
        val u3 = fun(user: User) = user.printMessage()
        val u4 = fun(user: User, afterYears: Int): String {
            user.age += afterYears
            return "高阶函数 - ${user.name} 今年 ${user.age - afterYears} 岁"
        }
        // fun User.() 和 fun(user: User)，是 User 的扩展函数,可以使用 user 直接访问
        // T.()->R
        val u5 = fun User.(){
            printMessage()
        }
        func2(u3, u4)
        println()
        func2(u5) { u, y -> "高阶函数 - ${u.name}在 $y 年后就是 ${u.age + y} 岁了" }
        println("高阶函数 - -----------------------------------------------")
    }

    private fun testFunc3() {
        val u6 = fun User.(){
            printMessage()
        }
        val u7 = fun User.(afterYears: Int):String{
            return "高阶函数 - 我是测试代码哈哈哈，afterYears - $afterYears"
        }
        func3(u6, u7)
        println("高阶函数 - -----------------------------------------------")
    }

    private fun testFunc4() {
        func4()()
        func4().invoke()
    }
}