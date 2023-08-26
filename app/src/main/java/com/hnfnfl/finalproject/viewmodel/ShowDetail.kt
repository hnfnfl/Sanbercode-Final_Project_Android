package com.hnfnfl.finalproject.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hnfnfl.finalproject.R
import com.hnfnfl.finalproject.databinding.BottomSheetAnimeDetailBinding
import com.hnfnfl.finalproject.db.AnimeEntity
import es.dmoral.toasty.Toasty

fun showDetailAnime(ctx: Context, viewModel: MainViewModel?, data: AnimeEntity, isHome: Boolean) {
    val binding = BottomSheetAnimeDetailBinding.inflate(LayoutInflater.from(ctx))
    val dialog = BottomSheetDialog(ctx).apply {
        setCancelable(true)
        setContentView(binding.root)
    }

    binding.apply {
        val eps = if (data.episodes != null && data.episodes != 0) data.episodes else 0
        val duration = if (data.duration != null) data.duration else "-"
        Glide.with(ctx).load(data.imageUrl).centerCrop().into(ivAnimeCoverArt)
        tvAnimeRatingScore.text = ctx.getString(R.string.tv_anime_rating_score, data.rating, data.score)
        tvAnimeTitle.text = data.title
        tvAnimeStatus.text = ctx.getString(R.string.tv_anime_status, data.status)
        tvAnimeEpisodes.text = ctx.getString(R.string.tv_anime_episodes, eps)
        tvAnimeDuration.text = ctx.getString(R.string.tv_anime_duration, duration)
        tvAnimeSynopsis.text = data.synopsis
        if (isHome && viewModel != null) {
            btnAddFav.visibility = View.VISIBLE
            btnAddFav.setOnClickListener {
                viewModel.addFavoriteAnime(data)
                Toasty.success(ctx, "Anime ${data.title} berhasil ditambahkan ke favorite", Toasty.LENGTH_SHORT).show()
            }
        } else {
            btnAddFav.visibility = View.GONE
        }
    }

    dialog.show()
}