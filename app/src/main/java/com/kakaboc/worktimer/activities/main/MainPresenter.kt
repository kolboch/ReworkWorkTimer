package com.kakaboc.worktimer.activities.main

import android.content.SharedPreferences
import com.kakaboc.worktimer.R
import com.kakaboc.worktimer.data.DaoMeasurements
import com.kakaboc.worktimer.data.SP_TIMER_STARTED
import com.kakaboc.worktimer.model.TimerWt
import com.kakaboc.worktimer.model.getCurrentSimpleDate
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

interface MainMVVM {
    interface Presenter {
        fun restoreTimerState()
        fun onTimerTriggered()
    }

    interface ViewModel {
        fun postMeasuredTime(formattedTime: String)
        fun postTimerImageSrc(imageSrc: Int)
    }
}

class MainPresenter(
        private val measurementsDao: DaoMeasurements,
        private val sharedPreferences: SharedPreferences,
        private val viewModel: MainMVVM.ViewModel
) : MainMVVM.Presenter {

    private val timer = TimerWt()
    private lateinit var updateCoroutine: Job

    override fun restoreTimerState() {
        val measurement = measurementsDao.selectMeasurementForDate(getCurrentSimpleDate())
        if (measurement != null) {
            timer.measuredTime = measurement.measuredTime.toLong()
        }
        if (wasTimerOn(sharedPreferences)) {
            startTimer()
        }
    }

    override fun onTimerTriggered() {
        if (timer.isRunning()) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    private fun wasTimerOn(sharedPrefs: SharedPreferences): Boolean {
        return sharedPrefs.getBoolean(SP_TIMER_STARTED, false)
    }

    private fun stopTimer() {
        timer.stopTimer()
        stopUpdateCoroutine()
        viewModel.postTimerImageSrc(R.drawable.timer_start_anim)
    }

    private fun startTimer() {
        timer.startTimer()
        startUpdateCoroutine()
        viewModel.postTimerImageSrc(R.drawable.timer_stop_anim)
    }

    private fun stopUpdateCoroutine() {
        updateCoroutine.cancel()
    }

    private fun startUpdateCoroutine() {
        updateCoroutine = launch(CommonPool) {
            while (true) {
                viewModel.postMeasuredTime(timer.getTimeMeasured().getFormattedTime())
                delay(1000L, TimeUnit.MILLISECONDS)
            }
        }
    }

}