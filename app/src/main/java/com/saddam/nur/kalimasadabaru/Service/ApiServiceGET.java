package com.saddam.nur.kalimasadabaru.Service;

import com.saddam.nur.kalimasadabaru.Model.ListUserLoginModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiServiceGET {

    @GET("user/login")
    Call<ListUserLoginModel> getUser(@Header("Authorization") String authHeader);
}
