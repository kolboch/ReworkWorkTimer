package com.kakaboc.worktimer.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.kakaboc.worktimer.model.MEASUREMENTS_DATE
import com.kakaboc.worktimer.model.Measurement
import com.kakaboc.worktimer.model.TABLE_MEASUREMENTS

@Dao
interface DaoMeasurements {
    @Insert
    fun insert(measurement: Measurement)

    @Delete
    fun delete(vararg measurements: Measurement)

    @Query("SELECT * FROM $TABLE_MEASUREMENTS")
    fun selectAllMeasurements(): LiveData<List<Measurement>>

    @Query("SELECT * FROM $TABLE_MEASUREMENTS WHERE $MEASUREMENTS_DATE= :measurementDate")
    fun selectMeasurementsForDate(measurementDate: String): Measurement?

    /**
     * update measurement based on primary key
     */
    @Update
    fun updateMeasurementForDate(measurement: Measurement): Int
}