package com.rome.tech.mytraining.superheros_app.service

import com.rome.tech.mytraining.superheros_app.model.SuperherosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/1531095577640835/search/{name}")
    suspend fun getSuperheros(@Path("name") superheroName: String): Response<SuperherosResponse>

//    @GET("/api/1531095577640835/{id}")
//    suspend fun getSuperheroDetail(@Path("id") superheroId:String):Response<>

}