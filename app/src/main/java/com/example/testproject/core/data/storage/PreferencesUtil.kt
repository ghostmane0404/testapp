package com.example.testproject.core.data.storage

import android.content.Context
import android.content.SharedPreferences

object PreferencesUtil {

    private lateinit var preferences: SharedPreferences

    private const val PREFERENCE_NAME = "com.example.APP_PREF"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }



    private const val ACCESS_TOKEN = "accessToken"

    private const val IS_APP_REVIEWED = "isReviewed"





    var accessToken:String?
        get() = preferences.getString(ACCESS_TOKEN,"").toString()
        set(value) {
            preferences.edit().putString(ACCESS_TOKEN,value).apply()
        }


    var isReviewed:Boolean
        get() = preferences.getBoolean(IS_APP_REVIEWED,false)
        set(value) {
            if (value != null) {
                preferences.edit().putBoolean(IS_APP_REVIEWED,value).apply()
            }
        }


}