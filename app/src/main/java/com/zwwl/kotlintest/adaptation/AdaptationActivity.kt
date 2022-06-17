package com.zwwl.kotlintest.adaptation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zwwl.kotlintest.R

class AdaptationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CustomDensity.setCustomDensity(this, application)
        setContentView(R.layout.activity_adaptation)
    }
}