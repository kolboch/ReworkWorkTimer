package com.kakaboc.worktimer.activities.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.kakaboc.worktimer.R
import com.kakaboc.worktimer.data.AppDatabase
import com.kakaboc.worktimer.data.SHARED_PREFERENCES_WT
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

class MainViewModel(application: Application) : AndroidViewModel(application), MainMVVM.ViewModel {

    private val LOG = "MainViewModel"


    val measuredTime: MutableLiveData<String> = MutableLiveData<String>().apply { value = "0:00:00" }
    val timerIsOn: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    private lateinit var presenter: MainMVVM.Presenter

    init {
        launch {
            withContext(CommonPool) {
                val measurementsDao = AppDatabase.getInstance(application.applicationContext).measurementsDao()
                val sharedPrefs = application.applicationContext.getSharedPreferences(SHARED_PREFERENCES_WT, Context.MODE_PRIVATE)
                presenter = MainPresenter(measurementsDao, sharedPrefs, this@MainViewModel)
                presenter.restoreTimerState()
            }
        }
    }

    override fun postMeasuredTime(formattedTime: String) {
        measuredTime.postValue(formattedTime)
    }

    override fun postTimerIsOnValue(isTimerOn: Boolean) {
        timerIsOn.value = isTimerOn
    }

    fun onTimerClicked() {
        presenter.onTimerTriggered()
    }
}