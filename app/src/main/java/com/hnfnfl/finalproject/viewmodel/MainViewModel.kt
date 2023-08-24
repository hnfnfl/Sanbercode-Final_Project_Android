package com.hnfnfl.finalproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hnfnfl.finalproject.db.AnimeEntity
import com.hnfnfl.finalproject.retrofit.AnimeResponse
import com.hnfnfl.finalproject.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : ViewModel() {

    val topAnimeLiveData: MutableLiveData<List<AnimeEntity>> = MutableLiveData()
    val upcomingAnimeLiveData: MutableLiveData<List<AnimeEntity>> = MutableLiveData()

    fun getTopAnime() {
        RetrofitClient.apiService.getTopAnime().enqueue(object : Callback<AnimeResponse> {
            override fun onResponse(call: Call<AnimeResponse>, response: Response<AnimeResponse>) {
                if (response.isSuccessful) {
                    val animeResponse = response.body()
                    animeResponse?.let {
                        val animeList = mutableListOf<AnimeEntity>()
                        for (animeData in it.data) {
                            val animeEntity = AnimeEntity(
                                animeData.malId,
                                animeData.url,
                                animeData.images.jpg.imageUrl,
                                animeData.title,
                                animeData.episodes,
                                animeData.status,
                                animeData.duration,
                                animeData.rating,
                                animeData.synopsis,
                            )
                            animeList.add(animeEntity)
                        }
                        topAnimeLiveData.postValue(animeList)
                    }
                } else {
                    Log.e("MainViewModel", "onResponse Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AnimeResponse>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ${t.message}")
            }
        })
    }

    fun getUpcomingAnime() {
        RetrofitClient.apiService.getUpcomingAnime().enqueue(object : Callback<AnimeResponse> {
            override fun onResponse(call: Call<AnimeResponse>, response: Response<AnimeResponse>) {
                if (response.isSuccessful) {
                    val animeResponse = response.body()
                    animeResponse?.let {
                        val animeList = mutableListOf<AnimeEntity>()
                        for (animeData in it.data) {
                            val animeEntity = AnimeEntity(
                                animeData.malId,
                                animeData.url,
                                animeData.images.jpg.imageUrl,
                                animeData.title,
                                animeData.episodes,
                                animeData.status,
                                animeData.duration,
                                animeData.rating,
                                animeData.synopsis,
                            )
                            animeList.add(animeEntity)
                        }
                        upcomingAnimeLiveData.postValue(animeList)
                    }
                } else {
                    Log.e("MainViewModel", "onResponse Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AnimeResponse>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ${t.message}")
            }
        })
    }
}