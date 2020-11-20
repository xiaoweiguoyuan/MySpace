package com.puci.qs.myspace.net;


import com.puci.qs.myspace.entity.CommentBean;
import com.puci.qs.myspace.entity.CommentEpisodeRes;
import com.puci.qs.myspace.entity.EpisodeDetail;
import com.puci.qs.myspace.entity.SubscribeBean;
import com.puci.qs.spacenet.http.response.Response;
import com.puci.qs.spacenet.http.utils.Constants;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiService {
    public static final String BASE_URL = "http://39.97.191.120:8000/";

    @GET(Constants.EPISODE_DETAIL_URL + "{id}" + "/comments")
    Call<ArrayList<CommentBean>> getComments(@Path("id") String id, @QueryMap Map<String, Object> map);

    @POST(Constants.EPISODE_DETAIL_URL + "{id}/comment?")
    Call<CommentEpisodeRes> commentEpisode(@Path("id") String id, @Body RequestBody body);

    @POST(Constants.EPISODE_DETAIL_URL + "{id}/like")
    Call<Void> like(@Path("id") String id);

    @GET(Constants.EPISODE_DETAIL_URL + "{id}")
    Call<EpisodeDetail> episodeDetail(@Path("id") String id);

    @GET(Constants.SUBSCRIBES)
    Call<SubscribeBean> getSubscribes(@QueryMap Map<String, Object> map);

    @POST(Constants.UPLOAD_EPISODE_LIST)
    Call<Response> uploadEpisodeList(@Body RequestBody body);

}
