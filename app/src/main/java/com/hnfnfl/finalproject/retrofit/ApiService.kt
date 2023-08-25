package com.hnfnfl.finalproject.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("anime")
    fun getAnime(
        @Query("q") name: String,
        @Query("limit") limit: Int? = null,
        @Query("sfw") sfw: Boolean? = true,
    ): Call<AnimeResponse>

    @GET("top/anime")
    fun getTopAnime(
        @Query("limit") limit: Int = 5,
    ): Call<AnimeResponse>

    @GET("seasons/upcoming")
    fun getUpcomingAnime(
        @Query("limit") limit: Int = 5,
    ): Call<AnimeResponse>
}