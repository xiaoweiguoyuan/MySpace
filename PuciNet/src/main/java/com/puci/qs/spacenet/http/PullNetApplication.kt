package com.puci.qs.spacenet.http

import android.app.Application
import com.puci.qs.basedependencies.IComponentApplication

open class PullNetApplication : IComponentApplication {

    companion object {
        private var myApplication: Application? = null

        open fun getInstance(): Application? {
            return myApplication
        }
    }

    override fun init(application: Application?) {
        myApplication = application
    }
}