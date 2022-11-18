package com.zwwl.kotlintest.func

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.zwwl.kotlintest.R
import com.zwwl.kotlintest.Utils
import kotlinx.android.synthetic.main.activity_func.*

/**
 * 高阶函数测试
 * 自定义沉浸式顶部导航
 */
class FuncActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_func)
        /**
         * 沉浸式状态栏：
         * https://guolin.blog.csdn.net/article/details/123023395
         * https://blog.csdn.net/litefish/article/details/53241081?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522166875709316800182754848%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=166875709316800182754848&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_ecpm_v1~rank_v31_ecpm-1-53241081-null-null.nonecase&utm_term=%E6%B5%85%E8%B0%88&spm=1018.2226.3001.4450
         *
         */
        //沉浸式状态栏设置
        window.statusBarColor = Color.TRANSPARENT
        // 稳定布局，避免闪烁。同时将布局内容拓展到状态的后面
        func_root.systemUiVisibility = (SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        // 监听window变化，其中 CoordinatorLayout 和 AppBarLayout 、CollapsingToolbarLayout 是存在 OnApplyWindowInsetsListener 的，所以他们的组合可以直接实现沉浸式效果
        ViewCompat.setOnApplyWindowInsetsListener(func_nav) { view, insets ->
            val params = view.layoutParams as FrameLayout.LayoutParams
            // 重新设置自定义导航栏高度
            params.height = insets.systemWindowInsetTop + Utils.dip2px(applicationContext, 50f)
            // 重新设置内容区域距离顶部的距离
            val rootContentParams = func_content_root.layoutParams as FrameLayout.LayoutParams
            rootContentParams.topMargin = params.height
            insets
        }

        testFunc1()
        testFunc2()
        testFunc3()
        testFunc4()

        func_nav_back.setOnClickListener { finish() }
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