package com.luyny.brawlbase

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BrawlStarsApi {
    @GET("v1/players/{playerTag}")
    fun getPlayerInfo(@Path("playerTag") playerTag: String): Call<ApiResponse>
//    @GET("/players/{playerTag}/battlelog")
//    fun getPlayerBattleLog(@Path("playerTag")playerTag: String):Call<ApiResponse>
}