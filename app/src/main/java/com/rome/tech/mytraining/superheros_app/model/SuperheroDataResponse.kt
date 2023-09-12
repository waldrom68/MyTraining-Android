package com.rome.tech.mytraining.superheros_app.model

import com.google.gson.annotations.SerializedName

data class SuperheroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val listDataTarget: List<SuperheroDataName>
)


data class SuperheroDataName(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)

