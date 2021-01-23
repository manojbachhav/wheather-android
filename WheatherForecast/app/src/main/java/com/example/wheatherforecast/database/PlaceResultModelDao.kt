package com.example.wheatherforecast.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.wheatherforecast.model.home.PlaceResultModel

@Dao
interface PlaceResultModelDao {

    @Query("SELECT * FROM PlaceResultModel ORDER BY pid DESC")
    fun getAll(): List<PlaceResultModel>

    @Insert
    fun insert(placeResultModel: PlaceResultModel)

}