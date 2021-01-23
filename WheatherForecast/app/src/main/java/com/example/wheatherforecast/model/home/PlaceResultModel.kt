package com.example.wheatherforecast.model.home

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class PlaceResultModel(
    @PrimaryKey val pid: Long,
    @ColumnInfo(name = "place_name") val name: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "observation_time") val time: Long?,
    @ColumnInfo(name = "temprature") val temp: Int?,
    @ColumnInfo(name = "climate_image") val image: String?,
    @ColumnInfo(name = "climate_status") val status: String?
) : Serializable {
}
