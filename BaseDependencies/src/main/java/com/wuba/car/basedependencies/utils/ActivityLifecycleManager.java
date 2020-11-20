package com.wuba.car.basedependencies.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;


public class ActivityLifecycleManager implements Application.ActivityLifecycleCallbacks {

    private List<Activity> mActivityStack = new LinkedList<>();
    private int activityCounter;


    private static class SingleTonHolder {
        final static ActivityLifecycleManager INSTANCE = new ActivityLifecycleManager();
    }

    public static ActivityLifecycleManager getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    public void init(Context context) {
        Application mApplication = (Application) context.getApplicationContext();
        mApplication.unregisterActivityLifecycleCallbacks(this);
        mApplication.registerActivityLifecycleCallbacks(this);
    }

    /**
     * 获取当前最上层的 Activity
     *
     * @return 最上层 Activity
     */
    public Activity getCurrentActivity() {
        return mActivityStack.size() > 0 ? mActivityStack.get(mActivityStack.size() - 1) : null;
    }

    /**
     * 获取当前 Activity 的上一个 Activity
     */
    public Activity getPreviousActivity() {
        if (mActivityStack.size() > 1) {
            return mActivityStack.get(mActivityStack.size() - 2);
        }
        return null;
    }

    public List<Activity> getActivities() {
        return mActivityStack;
    }

    public <T extends Activity> T getActivity(Class<T> clazz) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass() == clazz) {
                return (T) activity;
            }
        }
        return null;
    }


    public void finishAllActivity() {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        mActivityStack.clear();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityStack.add(activity);
    }


    @Override
    public void onActivityStarted(Activity activity) {
        activityCounter++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityCounter--;
        if (activityCounter < 0) {
            activityCounter = 0;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityStack.remove(activity);
    }


    /**
     * 判断当前应用是否处于后台
     *
     * @return
     */
    public boolean isInBackGround() {
        return activityCounter == 0;
    }
}
