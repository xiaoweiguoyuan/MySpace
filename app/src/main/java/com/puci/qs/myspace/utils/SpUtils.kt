package com.puci.qs.myspace.utils

import android.content.Context.MODE_PRIVATE
import com.puci.qs.myspace.MyApplication

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