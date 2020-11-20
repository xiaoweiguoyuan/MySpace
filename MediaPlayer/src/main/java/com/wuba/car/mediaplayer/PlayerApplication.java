package com.wuba.car.mediaplayer;

import android.app.Application;

import com.wuba.car.basedependencies.IComponentApplication;

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
