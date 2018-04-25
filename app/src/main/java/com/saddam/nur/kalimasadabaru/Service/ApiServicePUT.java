package com.saddam.nur.kalimasadabaru.Service;

import com.saddam.nur.kalimasadabaru.Model.PostResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServicePUT {
    @PUT("user/pemesanan")
    @FormUrlEncoded
    Call<PostResponseModel> updatePemesanan(@Query("id_pemesanan") String id_pemesanan, @Field("data_pemain") String data_pemain);
}
