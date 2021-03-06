package com.wuba.car.myspace.net;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.wuba.car.basedependencies.utils.SaveLog;
import com.wuba.car.myspace.entity.AllBean;
import com.wuba.car.myspace.entity.CommentBean;
import com.wuba.car.myspace.entity.CommentEpisodeRes;
import com.wuba.car.myspace.entity.EpisodeDetail;
import com.wuba.car.myspace.entity.ProgramBean;
import com.wuba.car.myspace.entity.SingleBean;
import com.wuba.car.myspace.entity.SubscribeBean;
import com.wuba.car.myspace.utils.Constants;
import com.wuba.car.qishuier.BuildConfig;
import com.wuba.car.spacenet.http.interceptor.CookieRequestInterceptor;
import com.wuba.car.spacenet.http.interceptor.DecodeConverterFactory;
import com.wuba.car.spacenet.http.response.Response;
import com.wuba.car.spacenet.http.service.MyTrustManager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * Api工厂类 (网络框架的搭建)
 */

public class HttpEngine {

    private static HttpEngine factory;
    private ApiService mApiInterface;

    private HttpEngine() {

        //声明日志类
        HttpLoggingInterceptor httpLog;
        if (BuildConfig.DEBUG) {
            httpLog = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    SaveLog.d(message);
                }
            });
            httpLog.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLog = new HttpLoggingInterceptor();
            httpLog.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写的超时时间
                .addInterceptor(new CookieRequestInterceptor())
                .addInterceptor(httpLog)
                .sslSocketFactory(MyTrustManager.createSSLSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(DecodeConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiInterface = retrofit.create(ApiService.class);
    }

    public synchronized static HttpEngine getInstance() {
        if (factory == null) {
            factory = new HttpEngine();
        }
        return factory;
    }

    public void getComments(@NotNull String id, Map<String, Object> map, @NotNull Callback<ArrayList<CommentBean>> callback) {
        mApiInterface.getComments(id, map).enqueue(callback);
    }

    public void sendComment(@Nullable String episoId, @NotNull String content, @NonNull Callback<CommentEpisodeRes> callback) {
        if (TextUtils.isEmpty(content)) return;
        if (BuildConfig.DEBUG) {
            content = content + "-Android";
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse(Constants.MediaTypeCons.JSON_TYPE), jsonObject.toString());
        if (TextUtils.isEmpty(episoId)) {
            episoId = "30";
        }
        mApiInterface.commentEpisode(episoId, body).enqueue(callback);
    }

    public void like(@NonNull String episoId, @NonNull Callback<Void> callback) {
        mApiInterface.like(episoId).enqueue(callback);
    }

    public void episodeDetail(@NonNull String episoId, @NonNull Callback<EpisodeDetail> callback) {
        mApiInterface.episodeDetail(episoId).enqueue(callback);
    }

    public void getSubscribes(String cursor, Callback<SubscribeBean> callback) {
        Map map = new HashMap<String, String>();
        if (!TextUtils.isEmpty(cursor)) {
            map.put("cursor", cursor);
        }
        mApiInterface.getSubscribes(map).enqueue(callback);
    }

    public void uploadEpisodeList(ArrayList<Integer> list, Callback<Response> callback) {
//        Map map = new HashMap<String, String>();
//        map.put("episode_list", list.toString());
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray(list);
        try {
            jsonObject.put("episode_list", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse(Constants.MediaTypeCons.JSON_TYPE), jsonObject.toString());
        mApiInterface.uploadEpisodeList(body).enqueue(callback);
    }


    public void searchByNet(@NonNull String keywork,@NonNull String type, @NonNull Callback<AllBean> callback) {
        mApiInterface.searchByNet(keywork,type).enqueue(callback);
    }

    public void searchProgramByNet(@NonNull String keywork,@NonNull String type, @NonNull Callback<ProgramBean> callback) {
        mApiInterface.searchProgramByNet(keywork,type).enqueue(callback);
    }


    public void searchSingleByNet(@NonNull String keywork,@NonNull String type, @NonNull Callback<SingleBean> callback) {
        mApiInterface.searchSingleByNet(keywork,type).enqueue(callback);
    }
}

