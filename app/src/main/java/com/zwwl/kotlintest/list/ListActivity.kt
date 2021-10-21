package com.zwwl.kotlintest.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zwwl.kotlintest.R
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        list_button1.setOnClickListener { startActivity(Intent(this@ListActivity, List1Activity::class.java)) }
        list_button2.setOnClickListener { startActivity(Intent(this@ListActivity, List2Activity::class.java)) }
        list_button3.setOnClickListener {  }
    }
}