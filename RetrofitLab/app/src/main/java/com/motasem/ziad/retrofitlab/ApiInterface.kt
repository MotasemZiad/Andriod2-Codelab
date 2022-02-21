package com.motasem.ziad.retrofitlab

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @POST("login_api")
    @FormUrlEncoded
    fun login(
        @Field("secret") secret: String,
        @Field("password") password: String,
        @Field("phone") phone: String
    ): Call<Login>
}