package com.kakaboc.worktimer.model

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

const val TABLE_MEASUREMENTS = "worktimer_measures"
const val MEASUREMENTS_DATE = "wt_date"
const val MEASUREMENTS_TIME = "wt_time"

@Entity(tableName = TABLE_MEASUREMENTS)
data class Measurement(
        @PrimaryKey
        @ColumnInfo(name = MEASUREMENTS_DATE)
        var measurementDate: String,
        @ColumnInfo(name = MEASUREMENTS_TIME)
        var measuredTime: Int
)

data class Time(val hours: Int, val minutes: Int, val seconds: Int) {
    fun getFormattedTime(): String {
        return String.format("%d:%02d:%02d", hours, minutes, seconds)
    }
}

@SuppressLint("SimpleDateFormat")
fun getCurrentSimpleDate(): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    return sdf.format(Date())
}

