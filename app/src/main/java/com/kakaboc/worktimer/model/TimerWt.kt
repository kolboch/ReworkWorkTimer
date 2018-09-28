package com.kakaboc.worktimer.model

// Wt suffix to not conflict java.util.Timer
class TimerWt {
    var measuredTime: Long = 0
    private var startTime: Long = 0
    private var isOn = false

    fun getTimeMeasured(): Time {
        val secondsTotal = (System.currentTimeMillis() - startTime + measuredTime).millisToSeconds()
        val minutesTotal = secondsTotal.secondsToMinutes()
        val hours = minutesTotal.minutesToHours()
        val minutes = minutesTotal - hours.hoursToMinutes()
        val seconds = secondsTotal - minutesTotal.minutesToSeconds()
        return Time(hours.toInt(), minutes.toInt(), seconds.toInt())
    }

    fun startTimer() {
        if (isOn) {
            return
        } else {
            startTime = System.currentTimeMillis()
            isOn = true
        }
    }

    fun stopTimer() {
        if (!isOn) {
            return
        } else {
            measuredTime += (System.currentTimeMillis() - startTime)
            isOn = false
        }
    }

    fun isRunning(): Boolean = isOn
}
