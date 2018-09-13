package com.kakaboc.worktimer.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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