package com.marta.picturesfromapi.apiStuff

import retrofit2.http.GET

const val BASE_URL = "https://thatcopy.pw/catapi/"

interface APIRequest {
    @GET("rest/")
    suspend fun getRandomDog(): ApiData
}