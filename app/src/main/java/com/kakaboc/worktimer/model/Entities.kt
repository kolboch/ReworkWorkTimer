package com.kakaboc.worktimer.model

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

const val TABLE_MEASUREMENTS = "worktimer_measures"

@Entity(tableName = TABLE_MEASUREMENTS)
data class Measurement(
        @ColumnInfo(name = "wt_date")
        var measurementDate: String,
        @ColumnInfo(name = "wt_time")
        var measuredTime: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

data class Time(val hours: Int, val minutes: Int, val seconds: Int)

class Timer {
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
}

@SuppressLint("SimpleDateFormat")
fun getCurrentSimpleDate(): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    return sdf.format(Date())
}