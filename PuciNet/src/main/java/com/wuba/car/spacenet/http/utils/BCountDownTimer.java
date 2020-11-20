package com.wuba.car.spacenet.http.utils;

import android.os.CountDownTimer;

/**
 * 倒计时工具
 */
public class BCountDownTimer extends CountDownTimer {
    private BCountdownTimerListener listener;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public BCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public void setDownTimerListener(BCountdownTimerListener listener) {
        this.listener = listener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (listener != null) {
            listener.onTick(millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if (listener != null) {
            listener.onFinish();
        }
    }

    public void onDestory() {
        cancel();
        listener = null;
    }

    public interface BCountdownTimerListener {
        void onTick(long millisUntilFinished);
        void onFinish();
    }
}
