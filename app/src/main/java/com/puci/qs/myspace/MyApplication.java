package com.puci.qs.myspace;

import android.app.Application;

import com.puci.qs.basedependencies.IComponentApplication;

public class MyApplication extends Application {

    private static MyApplication myApplication;

    private static final String[] MODULESLIST =
            {"com.puci.qs.mediaplayer.PlayerApplication", "com.puci.qs.spacenet.http.PullNetApplication", "com.puci.qs.basedependencies.BApplication"};
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
