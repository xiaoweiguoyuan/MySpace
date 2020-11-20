package com.puci.qs.myspace.utils

import android.graphics.Bitmap
import androidx.palette.graphics.Palette
import com.puci.qs.myspace.enum.PattleEnum

open class HSLUtils {
    companion object {

        open fun generatePalett(bitmap: Bitmap, color: Int, style: PattleEnum, callback: HSLCallBack) {
            Palette.from(bitmap).generate(object : Palette.PaletteAsyncListener {
                override fun onGenerated(palette: Palette?) {
                    palette?.let {
                        var colorResult = getColor(palette, style, color)
                        callback?.onHSL(colorResult)
                    }
                }
            })
        }

        open fun getColor(palette: Palette, style: PattleEnum, color: Int): Int {

            if (null == palette) {
                return 0
            }
            //柔和而暗的颜色
            var darkMutedColor = palette.getDarkMutedColor(color)
            //鲜艳和暗的颜色
            var darkVibrantColor = palette.getDarkVibrantColor(color)
            //亮和鲜艳的颜色
            var lightVibrantColor = palette.getLightVibrantColor(color)
            //亮和柔和的颜色
            var lightMutedColor = palette.getLightMutedColor(color)
            //柔和颜色
            var mutedColor = palette.getMutedColor(color)
            var vibrantColor = palette.getVibrantColor(color)

            when(style) {
                PattleEnum.darkMutedColor -> return darkMutedColor
                PattleEnum.darkVibrantColor -> return darkVibrantColor
                PattleEnum.lightVibrantColor -> return lightVibrantColor
                PattleEnum.lightMutedColor -> return lightMutedColor
                PattleEnum.lightMutedColor -> return lightMutedColor
                PattleEnum.mutedColor -> return mutedColor
                PattleEnum.vibrantColor -> return vibrantColor
            }
            return 0
        }
    }

    interface HSLCallBack {
        fun onHSL(color: Int)
    }
}