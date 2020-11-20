package com.wuba.car.myspace.utils

open class DurationUtils {

    fun getCurTime(progress: Long):String {
        var h = 0
        var minute = 0
        var second = 0
        var pro = progress
        var result = StringBuilder()

        h = (pro / 3600).toInt()
        pro -= h * 3600
        minute = (pro / 60).toInt()
        pro -= minute * 60
        second = pro.toInt()

        result.append(getFormat(h)).append(":").append(getFormat(minute)).append(":").append(getFormat(second))

        return result.toString()
    }

    private fun getFormat(h: Int): String {
        if (h == 0) {
            return "00"
        } else if ( h < 10) {
            return "0" + h
        } else {
            return h.toString()
        }
    }
}