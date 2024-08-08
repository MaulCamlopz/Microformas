package com.example.microformas.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("version")
    val version: String,
    @SerializedName("origin")
    var origin: String,
)
