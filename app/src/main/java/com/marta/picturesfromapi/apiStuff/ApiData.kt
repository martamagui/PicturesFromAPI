package com.marta.picturesfromapi.apiStuff

import com.google.gson.annotations.SerializedName

data class ApiData(
   @SerializedName("id") var id:Int,
   @SerializedName("url")val url:String
)
