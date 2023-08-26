package com.hnfnfl.finalproject.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.hnfnfl.finalproject.db.AnimeEntity
import com.hnfnfl.finalproject.repository.AnimeRepository

class FavoriteAnimeViewModel(application: Application) : ViewModel() {

    private val repository: AnimeRepository = AnimeRepository(application)
    fun getFavoriteAnime() = repository.getFavoriteAnime()
    fun deleteFavoriteAnime(anime: AnimeEntity) = repository.deleteFavoriteAnime(anime)

}

