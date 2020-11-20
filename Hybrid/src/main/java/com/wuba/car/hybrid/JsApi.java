package com.wuba.car.hybrid;

import android.util.Log;
import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

import wendu.dsbridge.CompletionHandler;
import wendu.dsbridge.DWebView;
import wendu.dsbridge.OnReturnValue;

public class JsApi {

    private PlayCallBack playCallBack;

    //添加到待播放列表
    @JavascriptInterface
    public void addEpisodeToPlayList(Object msg, CompletionHandler handler) {
        Log.e("@@@", msg + "［syn call］");
        handler.complete(msg+" [ asyn call]");
        if (playCallBack != null) {
            playCallBack.addEpisodeToPlayList(msg.toString());
        }
    }

    //播放
    @JavascriptInterface
    public void playEpisode(Object json) {
        //{"id":"undefined","url":"https://theue.me/episode/prison/","title":"063. 在监狱中虚度人生","showNotes":"null"}
        Log.e("@@@", json.toString());
        try {
            if (playCallBack != null) {
                playCallBack.playEpisode(json.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //调用h5
    public void callJs(DWebView dwebView, String method) {
//        dwebView.callHandler("addValue",new Object[]{3,4},new OnReturnValue<Integer>(){
//            @Override
//            public void onValue(Integer retValue) {
//                Log.d("jsbridge","call succeed,return value is "+retValue);
//            }
//        });
    }

    public void setPlayCallBack(PlayCallBack playCallBack) {
        this.playCallBack = playCallBack;
    }

    public interface PlayCallBack {
        void playEpisode(String json);
        void addEpisodeToPlayList(String toString);
    }
}