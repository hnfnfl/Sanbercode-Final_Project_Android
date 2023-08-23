package com.hnfnfl.finalproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime_data ORDER BY title ASC")
    fun getFavoriteAnime(): LiveData<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteAnime(anime: AnimeEntity)

    @Delete
    fun deleteFavoriteAnime(anime: AnimeEntity)
}