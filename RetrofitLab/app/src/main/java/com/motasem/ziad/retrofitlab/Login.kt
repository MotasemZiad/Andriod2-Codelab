package com.motasem.ziad.retrofitlab

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("user_type") var user_type: String,
    @SerializedName("token") var token: String,
    @SerializedName("status") var status: String,
    @SerializedName("phone") var phone: String,
    @SerializedName("code") var code: String,
    @SerializedName("message") var message: String
)
