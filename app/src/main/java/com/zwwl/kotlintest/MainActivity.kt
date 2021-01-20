package com.zwwl.kotlintest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zwwl.kotlintest.proxy.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "========onCreate")

        val human = Human(Gender.MALE)
        human.name = "张三"
        Log.d(TAG, "========${human.name}")

        val human2 = Human(Gender.FEMALE)
        human2.name = "王哈哈"
        Log.d(TAG, "========${human2.name}")

        val value = ProxySubject(RealSubject());
        value.doAction("我是代理")

        val proxy = DynamicProxyHandler(RealSubject()).getProxy<ISubject>()
        proxy.doAction("我是动态代理")

        // hook测试
        val hookUtils = HookUtils(applicationContext)
        hookUtils.hookTestAPI29()

    }

    //初始化块中的代码实际上会成为主构造函数的一部分
    init {
        val person: Person = Person("张三")
        Log.d(TAG, "========" + person.name)
        Log.d(TAG, "========" + person.testName)

        val derived = Derived("zhang", "san")
        val name = Utils.ID_NAME
        val u1 = Utils
        val u2 = Utils
        Log.d(TAG, "=====u1=" + u1 + ",u2=" + u2)
        val m1 = Man
        val m2 = Man
        Log.d(TAG, "=====m1=" + m1 + ",m2=" + m2)

        var testName = "sanzhang"

    }

    override fun onResume() {
        super.onResume()
        button1.setOnClickListener {
            startActivity(Intent(this, Page1Activity::class.java))
        }

        button2.setOnClickListener { startActivity(Intent(this, Page2Activity::class.java)) }
    }
}
