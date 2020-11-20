package com.wuba.car.myspace.utils;

import android.content.res.AssetManager;

import com.wuba.car.myspace.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetUtils {
    
    public static String getStringFromAsset(String apiName) {
        String fileName = "zaitaxiang.mp3";
        try {
            AssetManager assetManager = MyApplication.getInstance().getResources().getAssets();
            InputStream inputStream = assetManager.open(fileName);
            return getStringFromInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getStringFromInputStream(InputStream is) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            String line;
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
