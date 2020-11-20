package com.puci.qs.spacenet.http.interceptor;


import com.google.gson.TypeAdapter;
import com.puci.qs.basedependencies.utils.SaveLog;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class DecryptGsonConverterFactory<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    DecryptGsonConverterFactory(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String newValue = value.string();
        SaveLog.i("@@@-response", newValue);

        return adapter.fromJson(newValue);
    }

}