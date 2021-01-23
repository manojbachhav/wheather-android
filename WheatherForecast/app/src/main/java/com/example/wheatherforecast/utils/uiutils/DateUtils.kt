package com.example.wheatherforecast.utils.uiutils

import java.text.SimpleDateFormat
import java.util.*


class DateUtils private constructor() {


    companion object {
        const val DATE_FORMAT_MONTH_ABBREVIATION = "MMM"
        const val DATE_FORMAT_DATE_ABBREVIATION = "dd"
        const val DATE_FORMAT_YEAR_ABBREVIATION = "yyyy"
        const val TIME_FORMAT_ABBREVIATION = "h:mm a"

        fun stringFrom(milliseconds: Long, format: String): String? {
            val formatter = SimpleDateFormat(format)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliseconds
            return formatter.format(calendar.time)
        }

        fun getFormattedDateTime(milliseconds: Long): String? {
            return stringFrom(
                milliseconds,
                DATE_FORMAT_MONTH_ABBREVIATION
            ) + " " + stringFrom(
                milliseconds,
                DATE_FORMAT_DATE_ABBREVIATION
            ) + ", " + stringFrom(
                milliseconds,
                DATE_FORMAT_YEAR_ABBREVIATION
            )
        }
    }

}