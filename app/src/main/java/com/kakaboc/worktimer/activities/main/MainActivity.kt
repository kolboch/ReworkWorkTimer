package com.kakaboc.worktimer.activities.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.drawable.*
import android.os.Bundle
import android.os.Handler
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.kakaboc.worktimer.R
import com.kakaboc.worktimer.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class MainActivity : AppCompatActivity(), MainMVVM.View {

    private val LOG = MainActivity::class.java.simpleName
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    private val longestAnimationTime = 1000L
    private var drawableStartAnimation: AnimatedVectorDrawableCompat? = null
    private var drawableStopAnimation: AnimatedVectorDrawableCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        prepareDrawables()
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.view = this
        viewModel.timerIsOn.observe(this, Observer<Boolean> { it?.let { timerOnOffObserver(it) } })
    }

    private fun prepareDrawables() {
        drawableStartAnimation = AnimatedVectorDrawableCompat.create(applicationContext, R.drawable.timer_start_anim)
        drawableStopAnimation = AnimatedVectorDrawableCompat.create(applicationContext, R.drawable.timer_stop_anim)
    }

    private fun timerOnOffObserver(isTimerOn: Boolean) {
        val toAnimate = timeButton.background as Animatable
        if (isTimerOn) {
            Log.v(LOG, "TT Value changed to true")
            toAnimate.start()
            Handler().postDelayed({
                toAnimate.stop()
                timeButton.setBackgroundResource(R.drawable.timer_stop_anim)
            }, longestAnimationTime)
        } else {
            Log.v(LOG, "FF Value changed to false")
            toAnimate.start()
            Handler().postDelayed({
                toAnimate.stop()
                timeButton.setBackgroundResource(R.drawable.timer_start_anim)
            }, longestAnimationTime)
        }
    }
}
