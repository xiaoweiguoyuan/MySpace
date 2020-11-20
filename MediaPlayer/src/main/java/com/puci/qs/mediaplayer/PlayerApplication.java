package com.puci.qs.mediaplayer;

import android.app.Application;

import com.puci.qs.basedependencies.IComponentApplication;

public class PlayerApplication implements IComponentApplication {

    private static Application myApplication;


    public static Application getInstance() {
        return myApplication;
    }

    @Override
    public void init(Application application) {
        myApplication = application;
    }
}
