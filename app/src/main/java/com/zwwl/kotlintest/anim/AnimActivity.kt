package com.zwwl.kotlintest.anim

import android.animation.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.zwwl.kotlintest.R
import kotlinx.android.synthetic.main.activity_anim.*

import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView


class AnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)
        anim1()
        content_layout.visibility = View.GONE
        loading_layout.postDelayed(Runnable {
            showContent()
        }, 1000)
    }

    private fun showContent() {
        val integer = getResources().getInteger(
            android.R.integer.config_shortAnimTime
        )
        content_layout.alpha = 0f
        content_layout.visibility = View.VISIBLE
        content_layout.animate().alpha(1f).setDuration(500).setListener(null)

        loading_layout.animate().alpha(0f).setDuration(500).setListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                loading_layout.visibility = View.GONE
            }
        })
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

        button11.setOnClickListener { showImage1() }
        button1.setOnClickListener { image1.startAnimation(anim1) }
        button2.setOnClickListener { set.start() }
        image1.setOnClickListener { Toast.makeText(this, "我是图片1", Toast.LENGTH_SHORT).show() }
        image2.setOnClickListener { Toast.makeText(this, "我是图片2", Toast.LENGTH_SHORT).show() }

//        image1.animate()!!.translationX(100f).setDuration(3000).start()
//        image2.animate()!!.translationX(100f).setDuration(3000).start()
    }

    private fun showImage1() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cx:Double = image1.width / 2.0
            val cy:Double = image1.height / 2.0
            val finalRadius:Double = Math.hypot(cx, cy)

            val anim : Animator = ViewAnimationUtils.createCircularReveal(image1, cx.toInt(), cy.toInt(), 0f, finalRadius.toFloat())
            image1.visibility = View.VISIBLE
            anim.start()
        } else {
            image1.visibility = View.INVISIBLE
        }
    }
}