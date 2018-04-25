package com.saddam.nur.kalimasadabaru.Service;

import com.saddam.nur.kalimasadabaru.Model.PostResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServicePOST {
    @FormUrlEncoded
    @POST("user/daftar")
    Call<PostResponseModel> InsertPost(@Field("nama") String nama, @Field("email") String email, @Field("password") String password, @Field("no_hp") String no_hp, @Field("alamat") String alamat, @Field("bank") String bank, @Field("no_rek") String no_rek, @Field("atas_nama") String atasnama);

    @FormUrlEncoded
    @POST("user/pemesanan")
    Call<PostResponseModel> InsertPesanan(@Field("id_pemesanan") String id_pemesanan, @Field("id_user") String id_user, @Field("nama_team") String nama_team, @Field("url_desain") String url_desain, @Field("url_logo") String url_logo, @Field("kategori") String kategori , @Field("jml_pesanan") String jml_pesanan);

}
