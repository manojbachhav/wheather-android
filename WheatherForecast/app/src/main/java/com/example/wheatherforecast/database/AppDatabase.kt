package com.example.wheatherforecast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wheatherforecast.model.home.PlaceResultModel


@Database(entities = arrayOf(PlaceResultModel::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeResultModelDao(): PlaceResultModelDao

    companion object {

        @Volatile
        private var appDatabaseInstance: AppDatabase? = null

        fun getDatabase(context: Context?): AppDatabase? {
            if (appDatabaseInstance == null) {
                synchronized(AppDatabase::class.java) {
                    if (appDatabaseInstance == null) {
                        appDatabaseInstance = Room.databaseBuilder(
                            context!!,
                            AppDatabase::class.java, "place-database"
                        ).build()
                        return appDatabaseInstance
                    }
                }
            }
            return appDatabaseInstance

        }
    }
}
