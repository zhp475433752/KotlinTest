package com.zwwl.kotlintest.anim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zwwl.kotlintest.R
import kotlinx.android.synthetic.main.activity_main_anim.*

class MainAnimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_anim)

        anim_btn1.setOnClickListener { startActivity(Intent(this, AnimActivity::class.java)) }
        anim_btn2.setOnClickListener { startActivity(Intent(this, ShareFirstActivity::class.java)) }
    }
}