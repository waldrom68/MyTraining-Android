package com.rome.tech.mytraining.superheros_app.model

import com.google.gson.annotations.SerializedName

data class SuperherosResponse(

    @SerializedName("response") val response: String,
    @SerializedName("results") val superheros: List<Superhero>

)

data class Superhero(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val superheroName: String
)