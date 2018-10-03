package com.kakaboc.worktimer.activities.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.kakaboc.worktimer.R
import com.kakaboc.worktimer.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainMVVM.View {

    private val LOG = MainActivity::class.java.simpleName
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.view = this
        prepareViews()
    }

    private fun prepareViews() {
        timeButton.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.timer_start_anim))
    }

    override fun onTimerClicked(view: View) {
        viewModel.onTimerClicked()
        val timerDrawable = (view as ImageView).drawable
        if (timerDrawable is Animatable) {
            timerDrawable.start()
        }
    }
}
