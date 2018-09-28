package com.kakaboc.worktimer.activities.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kakaboc.worktimer.R
import com.kakaboc.worktimer.databinding.ActivityMainBinding
import com.kakaboc.worktimer.model.TimerWt

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
//        timeButton.setOnClickListener {
//            val toAnimate = timeButton.drawable
//            if (toAnimate is Animatable) {
//                toAnimate.start()
//            }
//        }
    }
}
