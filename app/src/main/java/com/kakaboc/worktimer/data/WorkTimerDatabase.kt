package com.kakaboc.worktimer.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kakaboc.worktimer.model.Measurement

const val DATABASE_NAME = "worktimer_database"

@Database(entities = [Measurement::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun measurementsDao(): DaoMeasurements

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
    })
}

// reusable singleton holder with doubled check locking
open class SingletonHolder<out T, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}
