package com.hnfnfl.finalproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hnfnfl.finalproject.db.AnimeEntity
import com.hnfnfl.finalproject.repository.AnimeRepository
import com.hnfnfl.finalproject.retrofit.AnimeResponse
import com.hnfnfl.finalproject.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeListViewModel(application: Application) : ViewModel() {

    private val repository: AnimeRepository = AnimeRepository(application)
    fun addFavoriteAnime(anime: AnimeEntity) = repository.insertFavoriteAnime(anime)

    val liveData: MutableLiveData<List<AnimeEntity>> = MutableLiveData()

    fun getAnime(query: String) {
        RetrofitClient.apiService.getAnime(query).enqueue(object : Callback<AnimeResponse> {
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
                                animeData.score,
                                animeData.synopsis,
                            )
                            animeList.add(animeEntity)
                        }
                        liveData.postValue(animeList)
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

