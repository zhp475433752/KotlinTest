package com.zwwl.kotlintest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.zwwl.kotlintest.json.TestBean
import com.zwwl.kotlintest.list.ListActivity
import com.zwwl.kotlintest.media.camera.CameraActivity
import com.zwwl.kotlintest.media.exo.ExoPlayerActivity
import com.zwwl.kotlintest.media.mediaplayer.MediaPlayerActivity
import com.zwwl.kotlintest.media.videoview.VideoViewActivity
import com.zwwl.kotlintest.proxy.DynamicProxyHandler
import com.zwwl.kotlintest.proxy.ISubject
import com.zwwl.kotlintest.proxy.ProxySubject
import com.zwwl.kotlintest.proxy.RealSubject
import com.zwwl.kotlintest.thread.MyThread
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    var remoteName = ""

    //    var iAppManager: IAppManager? = null;
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
////        val hookUtils = HookUtils(applicationContext)
////        if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
////            hookUtils.hookTestAPI26()
////        } else {
////            hookUtils.hookTestAPI29()
////        }
//
//        iAppManager = IAppManager.Stub.asInterface(AppManagerBinder())

        try {
            val forName = Class.forName("com.zwwl.kotlintest.ref.TestAo")
            val declaredMethod =
                forName.getDeclaredMethod("get", Integer::class.java, Object::class.java)
            declaredMethod.isAccessible = true
            val result = declaredMethod.invoke(forName.newInstance(), 7, Integer.valueOf(0)) as Int
            Log.d("###########", "result = " + result)
        } catch (e: Exception) {
            Log.e("###########", "异常----" + e.message)
        }
        //102437 16879653
        val intValue = 16879653
        val a1 = -16777217 and intValue
        val a2 = intValue or 16777216
        Log.e("###########", "处理a1=" + a1)
        Log.e("###########", "处理a2=" + a2)
        getMethod()

        val testBean = TestBean("123", "zhang")
        val toJSONString = JSON.toJSONString(testBean)
        println("testBean=$toJSONString")

        initView()
    }

    private fun initView() {
        button1.setOnClickListener {
            onToast()
            onStartPage()
        }

        button2.setOnClickListener { startActivity(Intent(this, Page2Activity::class.java)) }
        button3.setOnClickListener { startActivity(Intent(this, MainActivity2::class.java)) }
        button4.setOnClickListener { startActivity(Intent(this, MediaPlayerActivity::class.java)) }
        button5.setOnClickListener { startActivity(Intent(this, VideoViewActivity::class.java)) }
        button6.setOnClickListener { startActivity(Intent(this, ExoPlayerActivity::class.java)) }
        button7.setOnClickListener { startActivity(Intent(this, CameraActivity::class.java)) }
        button8.setOnClickListener { startActivity(Intent(this, ListActivity::class.java)) }

        threadTest()
    }

    fun getMethod() {
        try {
            val forName = Class.forName("com.zwwl.kotlintest.ref.TestEnum")
            val declaredMethod = forName.getDeclaredMethod(
                "a",
                Int::class.java,
                arrayOf<Any>(
                    Int::class.java,
                    Int::class.java,
                    Int::class.java,
                    String::class.java
                ).javaClass
            )
            declaredMethod.isAccessible = true
            declaredMethod.invoke(
                forName.enumConstants!!.get(0),
                16055,
                arrayOf(2, 14, 1, "weixinid")
            )
            Log.d("###########", "TestAo - a 调用结束")
        } catch (e: java.lang.Exception) {
            Log.e("###########", "ServiceH - 异常----" + e.message)
        }
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
    }

    override fun onPause() {
        super.onPause()
        handler.sendEmptyMessageDelayed(1, 5000);
    }

    private fun onStartPage() {
        Log.e("########", "我是APP的点击方法 onStartPage")
        startActivity(Intent(this, Page1Activity::class.java))
    }

    fun onToast() {
//        Toast.makeText(this, iAppManager?.name, Toast.LENGTH_SHORT).show()
        val forName = Class.forName("com.zwwl.kotlintest.proxy.HookUtils")
        val declaredMethod = forName.getDeclaredMethod("getName", String::class.java)
        declaredMethod.isAccessible = true
        val invoke = declaredMethod.invoke(null, "我是反射名字")
        Toast.makeText(this, invoke as String, Toast.LENGTH_SHORT).show()
    }

    private fun threadTest() {
        val thread = MyThread()
        thread.start()
        Thread.sleep(500)
        thread.interrupt()
        Log.d(TAG, "thread线程 ${thread.name} 是否停止 = " + thread.isInterrupted)
        Thread.currentThread().interrupt();
        Log.d(TAG, "运行线程 ${Thread.currentThread().name} 是否停止 = ${Thread.interrupted()}")
        Log.d(TAG, "运行线程 ${Thread.currentThread().name} 是否停止 = ${Thread.interrupted()}")

    }

    private val handler = Handler(object : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {

            return false
        }

    });

}
