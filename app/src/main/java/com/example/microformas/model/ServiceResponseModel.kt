package com.example.microformas.model

import com.google.gson.annotations.SerializedName

data class ServiceResponseModel(
    @SerializedName("statusCode")
    val statusCode: Long,
    @SerializedName("data")
    val data: List<ServiceModel>,
    @SerializedName("messages")
    val messages: List<Any?>,
)