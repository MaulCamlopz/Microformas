package com.example.microformas.model

import com.google.gson.annotations.SerializedName

data class ServiceModel(
    @SerializedName("idServicio")
    val id: Long,
    @SerializedName("descServicio")
    val description: String,
    @SerializedName("noSerie")
    val sn: String,
    @SerializedName("telefono")
    val phone: String,
    @SerializedName("fecAtencion")
    val date: String,
    @SerializedName("idFalla")
    val idIssue: Long
)
