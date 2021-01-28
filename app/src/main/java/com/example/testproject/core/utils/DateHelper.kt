package com.example.testproject.core.utils

import android.annotation.SuppressLint
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getCurrentDate(): String {
            val currentTime = Calendar.getInstance().time
            val format = SimpleDateFormat("HH:mm:ss dd/M/yyyy")
            return format.format(currentTime)
        }

        @SuppressLint("SimpleDateFormat")
        fun formatForUi(date: Date):String{
            val sdf = SimpleDateFormat("hh:mm:ss EEE ")
            return sdf.format(date)
        }
        fun formatForBack(): Timestamp {
            val currentTime = Calendar.getInstance().time
            return Timestamp(currentTime)
        }
        fun getTimeFromDate(date: Date):String{
            val sdf = SimpleDateFormat("hh:mm")
            return sdf.format(date)
        }
        fun getWorkTime(date:String?):String?{
            val date1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date)
            val format = SimpleDateFormat("HH:mm")
            return format.format(date1)
        }

        fun getBirthday(date:String?):String?{
            val date1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date)
            val format = SimpleDateFormat("dd-MM-yyyy")
            return format.format(date1)
        }

        @SuppressLint("SimpleDateFormat")
        fun getCurrentTime(): String {
            val currentTime = Calendar.getInstance().time
            val format = SimpleDateFormat("HH:mm:ss")
            return format.format(currentTime)
        }
        fun getTime(time:String){

        }
        fun getYear(date:String):String{
            val date1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss").parse(date)
            val format = SimpleDateFormat("yyyy")
            return format.format(date1)
        }

        fun convertBirtdayToTimeServer(frontDate:String):String{
            val date1 = SimpleDateFormat("dd : MM : yyyy").parse(frontDate)
            //get current time with timezone
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
            return dateFormat.format(date1)
        }
    }




}