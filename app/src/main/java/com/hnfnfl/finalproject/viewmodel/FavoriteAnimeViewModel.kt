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

class FavoriteAnimeViewModel(application: Application) : ViewModel() {

    private val repository: AnimeRepository = AnimeRepository(application)
    fun getFavoriteAnime() = repository.getFavoriteAnime()

    fun deleteFavoriteAnime(anime: AnimeEntity) = repository.deleteFavoriteAnime(anime)

}

