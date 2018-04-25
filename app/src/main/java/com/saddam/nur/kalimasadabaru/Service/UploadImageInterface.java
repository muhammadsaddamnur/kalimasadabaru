package com.saddam.nur.kalimasadabaru.Service;

import com.saddam.nur.kalimasadabaru.Model.PostResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadImageInterface{

    @Multipart
    @POST("user/upload")
    Call<PostResponseModel> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);
}
