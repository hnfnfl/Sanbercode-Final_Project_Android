package com.hnfnfl.finalproject.repository

import androidx.recyclerview.widget.DiffUtil
import com.hnfnfl.finalproject.db.AnimeEntity

class AnimeCallback(private val oldList: List<AnimeEntity>, private val newList: List<AnimeEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].malId == newList[newItemPosition].malId
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAnime = oldList[oldItemPosition]
        val newAnime = newList[newItemPosition]
        return oldAnime.title == newAnime.title && oldAnime.episodes == newAnime.episodes && oldAnime.status == newAnime.status && oldAnime.duration == newAnime.duration && oldAnime.rating == newAnime.rating && oldAnime.synopsis == newAnime.synopsis
    }
}