package com.kakaboc.worktimer.activities.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.kakaboc.worktimer.data.AppDatabase
import com.kakaboc.worktimer.data.DaoMeasurements
import com.kakaboc.worktimer.model.TimerWt
import com.kakaboc.worktimer.model.getCurrentSimpleDate
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val LOG = "MainViewModel"
    private val timer = MutableLiveData<TimerWt>()
    private lateinit var measurementsDao: DaoMeasurements
    val measuredTime: String = "0:00:07"

    init {
        launch {
            withContext(CommonPool) {
                measurementsDao = AppDatabase.getInstance(application.applicationContext).measurementsDao()
                val measurement = measurementsDao.selectMeasurementsForDate(getCurrentSimpleDate())
                Log.v(LOG, "measurement is null: ${measurement == null}")
            }
        }
        timer.value = TimerWt()
    }


    fun getTimer(): LiveData<TimerWt> {
        return timer
    }
}