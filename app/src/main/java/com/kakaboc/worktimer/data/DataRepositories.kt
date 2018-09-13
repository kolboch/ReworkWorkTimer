package com.kakaboc.worktimer.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kakaboc.worktimer.model.Measurement
import com.kakaboc.worktimer.model.TABLE_MEASUREMENTS

@Dao
interface DaoMeasurements {
    @Insert
    fun insert(measurement: Measurement)

    @Delete
    fun delete(vararg measurements: Measurement)

    @Query("SELECT * FROM $TABLE_MEASUREMENTS")
    fun selectAllMeasurements(): List<Measurement>

    @Query("SELECT * FROM $TABLE_MEASUREMENTS WHERE wt_date= :measurementDate")
    fun selectMeasurementsForDate(measurementDate: String): List<Measurement>
}