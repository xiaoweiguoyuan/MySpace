package com.wuba.car.myspace.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.wuba.car.myspace.MyApplication

open class SpUtils {

    companion object {
        val sharedPreferences = MyApplication.getInstance().getSharedPreferences("QSER", MODE_PRIVATE)

        fun saveString(key: String, value: String) {

            sharedPreferences.edit().putString(key, value).commit()
        }

        fun getString(key: String): String? {
            return sharedPreferences.getString(key, "")
        }
    }
}