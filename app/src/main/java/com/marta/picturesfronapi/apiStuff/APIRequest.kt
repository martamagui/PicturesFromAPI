package com.marta.picturesfronapi.apiStuff

import retrofit2.http.GET

const val BASE_URL = "https://random.dog"

interface APIRequest {
    @GET("/woof.json?ref=apilist.fun")
    suspend fun getRandomDog():ApiData
}