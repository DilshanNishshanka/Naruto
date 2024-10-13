package com.creative.app.sl.naruto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://narutodb.xyz/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ApiService{
    @GET("character")
    suspend fun getCharacters(): NarutoResponse
}