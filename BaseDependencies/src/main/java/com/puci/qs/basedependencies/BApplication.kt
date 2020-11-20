package com.puci.qs.basedependencies

import android.app.Application
import com.puci.qs.basedependencies.utils.ActivityLifecycleManager

open class BApplication: IComponentApplication {
    private var myApplication: Application? = null


    open fun getInstance(): Application? {
        return myApplication
    }

    override fun init(application: Application?) {
        myApplication = application
        ActivityLifecycleManager.getInstance().init(myApplication)
    }
}