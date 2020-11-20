package com.wuba.car.myspace;

import android.app.Application;

import com.wuba.car.basedependencies.IComponentApplication;

public class MyApplication extends Application {

    private static MyApplication myApplication;

    private static final String[] MODULESLIST =
            {"com.wuba.car.mediaplayer.PlayerApplication", "com.wuba.car.spacenet.http.PullNetApplication", "com.wuba.car.basedependencies.BApplication"};
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        modulesApplicationInit();
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    private void modulesApplicationInit(){
        for (String moduleImpl : MODULESLIST){
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IComponentApplication){
                    ((IComponentApplication) obj).init(getInstance());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
