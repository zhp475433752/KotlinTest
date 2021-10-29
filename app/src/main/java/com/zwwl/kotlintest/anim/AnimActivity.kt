package com.zwwl.kotlintest.anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.zwwl.kotlintest.R
import kotlinx.android.synthetic.main.activity_anim.*

import android.animation.AnimatorInflater

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.widget.ImageView


class AnimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)
        anim1()

    }

    private fun anim1() {
        val anim1 = AnimationUtils.loadAnimation(this, R.anim.anim_view)
//        val anim2 = ObjectAnimator.ofFloat(image2, "translationX", 500f)
//        val anim3 = ObjectAnimator.ofFloat(image2, "translationY", 500f)
//        val animatorSet = AnimatorSet()
//        animatorSet.playTogether(anim2, anim3)
//        animatorSet.start()
//        ObjectAnimator.ofPropertyValuesHolder()

        val set = AnimatorInflater.loadAnimator(this, R.animator.animator_property) as AnimatorSet
        set.setTarget(image2)

        button1.setOnClickListener { image1.startAnimation(anim1) }
        button2.setOnClickListener { set.start() }
        image1.setOnClickListener { Toast.makeText(this, "我是图片1", Toast.LENGTH_SHORT).show() }
        image2.setOnClickListener { Toast.makeText(this, "我是图片2", Toast.LENGTH_SHORT).show() }

        image1.animate()!!.translationX(100f).setDuration(3000).start()
        image2.animate()!!.translationX(100f).setDuration(3000).start()
    }
}