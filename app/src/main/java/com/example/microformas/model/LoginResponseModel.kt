package com.example.microformas.model

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("access_token")
    val token: String,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("descEnv")
    val descEnv: String,
)
