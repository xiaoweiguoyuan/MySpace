package com.wuba.car.myspace.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.wuba.car.basedependencies.entity.Episode
import com.wuba.car.myspace.entity.EpisodeDetail
import java.lang.Exception

open class EpisodeUtils {

    companion object {
        fun episodeFromCurEpisode(episode: EpisodeDetail.CurEpisode): Episode? {
            if (episode != null) {
                var gs = Gson()
                val toJson = gs.toJson(episode)
                return gs.fromJson(toJson, Episode::class.java)
            }
            return null
        }

        fun curEpisodeFromEpisode(episode: Episode): EpisodeDetail.CurEpisode? {
            if (episode != null) {
                var gs = Gson()
                val toJson = gs.toJson(episode)
                return gs.fromJson(toJson, EpisodeDetail.CurEpisode::class.java)
            }
            return null
        }

        fun episodeToStr(episode: Episode): String {
            var gs = Gson()
            val toJson = gs.toJson(episode)
            return toJson
        }

        fun episodeFromStr(value: String?): Episode? {
            if (TextUtils.isEmpty(value)) return null
            try {
                var gs = Gson()
                return gs.fromJson(value, Episode::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }
}