package com.example.mahasiswa.API;

import com.example.mahasiswa.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("ambil.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("buat.php")
    Call<ResponseModel> ardCreateData(
            @Field("nama") String nama,
            @Field("nim") String nim
    );



    @FormUrlEncoded
    @POST("hapus.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("rinci.php")
    Call<ResponseModel> ardGetDetail(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("ubah.php")
    Call<ResponseModel> ardEditData(
            @Field("id") int id,
            @Field("nama") String nama,
            @Field("nim") String nim
    );

}
