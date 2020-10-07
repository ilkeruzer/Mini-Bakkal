package com.ilkeruzer.minibakkal.ui.activity

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.ilkeruzer.minibakkal.R
import com.ilkeruzer.minibakkal.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        backgroundAnim()
        openActivity()
    }

    private fun backgroundAnim() {
        val anim: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            binding.imageViewBackground,
            PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f)
        )

        anim.repeatCount = 1
        anim.repeatMode = ValueAnimator.REVERSE
        anim.duration = 1000
        anim.start()

        val animation: Animation = TranslateAnimation(0F, 300F, 0F, 0F)
        animation.duration = 7000
        animation.fillAfter = true
        binding.imageViewBackground.startAnimation(animation)


    }

    private fun openActivity() {
        val r = Runnable {
            goToMain()
        }
        Handler(Looper.myLooper()!!).postDelayed(r, 7500.toLong())
    }

    private fun goToMain() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}