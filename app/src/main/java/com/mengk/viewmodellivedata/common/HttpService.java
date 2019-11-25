package com.mengk.viewmodellivedata.common;


import java.util.Map;

import com.mengk.viewmodellivedata.model.bean.UploadResBean;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Desc
 * Date 2019/11/1
 * author mengk
 */
public interface HttpService {
    @Multipart
    @POST("upload")
    Call<UploadResBean> upload(@Part("uid") String uid, @Part("type") String type, @Part MultipartBody.Part file);

    @Multipart
    @POST("upload")
    Call<UploadResBean> uploadFiles(@Part("uid") String uid, @Part("type") String type, @PartMap() Map<String, RequestBody> maps);
}
