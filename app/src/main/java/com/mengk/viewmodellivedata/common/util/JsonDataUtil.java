package com.mengk.viewmodellivedata.common.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 测试数据json
 */
public class JsonDataUtil {
    /**
     * 获取json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        Log.e("===z","11112");
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            Log.e("===z","111123");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("===z","11114");
        }
        Log.e("===z",stringBuilder.toString());
        return stringBuilder.toString();
    }

}
