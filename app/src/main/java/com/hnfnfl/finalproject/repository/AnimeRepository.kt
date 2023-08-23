package com.hnfnfl.finalproject.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.hnfnfl.finalproject.db.AnimeDao
import com.hnfnfl.finalproject.db.AnimeDatabase
import com.hnfnfl.finalproject.db.AnimeEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AnimeRepository(application: Application) {

    private val animeDao: AnimeDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val database = AnimeDatabase.getDatabase(application)
        animeDao = database.animeDao()
    }

    fun getFavoriteAnime(): LiveData<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    fun insertFavoriteAnime(anime: AnimeEntity) = executorService.execute { animeDao.insertFavoriteAnime(anime) }

    fun deleteFavoriteAnime(anime: AnimeEntity) = executorService.execute { animeDao.deleteFavoriteAnime(anime) }
}