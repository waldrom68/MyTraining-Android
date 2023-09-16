package com.rome.tech.mytraining.superheros_app.model

import com.google.gson.annotations.SerializedName

data class SuperheroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: Powerstats,
    @SerializedName("image") val superheroImage: SuperheroImageDetailResponse,
)

data class Powerstats (
    @SerializedName("intelligence") val intelligence:String,
    @SerializedName("strength") val strength:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("durability") val durability:String,
    @SerializedName("power") val power:String,
    @SerializedName("combat") val combat:String,
)

class SuperheroImageDetailResponse (
    @SerializedName("url") val superheroImgUrl: String
)

data class Biography(
    @SerializedName("udsfgrl") val superherol: String,
)

