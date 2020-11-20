package com.wuba.car.spacenet.http

import android.app.Application
import com.wuba.car.basedependencies.IComponentApplication

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