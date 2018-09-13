package com.kakaboc.worktimer.activities

import android.graphics.drawable.Animatable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kakaboc.worktimer.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timeButton.setOnClickListener {
            val toAnimate = timeButton.drawable
            if(toAnimate is Animatable) {
                toAnimate.start()
            }
        }
    }
}
