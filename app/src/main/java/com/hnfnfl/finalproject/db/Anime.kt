package com.hnfnfl.finalproject.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "anime_data")
@Parcelize
data class AnimeEntity(
    @PrimaryKey var malId: Int = 0,
    @ColumnInfo(name = "url") var url: String? = null,
    @ColumnInfo(name = "image_url") var imageUrl: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "episodes") var episodes: Int? = null,
    @ColumnInfo(name = "status") var status: String? = null,
    @ColumnInfo(name = "duration") var duration: String? = null,
    @ColumnInfo(name = "rating") var rating: String? = null,
    @ColumnInfo(name = "score") var score: Double? = null,
    @ColumnInfo(name = "synopsis") var synopsis: String? = null,
) : Parcelable

data class PaginationData(
    val lastVisiblePage: Int,
    val hasNextPage: Boolean,
    val currentPage: Int,
)