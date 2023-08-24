package com.hnfnfl.finalproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnfnfl.finalproject.databinding.ItemAnimeTopBinding
import com.hnfnfl.finalproject.db.AnimeEntity
import com.hnfnfl.finalproject.repository.AnimeCallback
import com.hnfnfl.finalproject.viewmodel.MainViewModel
import es.dmoral.toasty.Toasty

class TopAnimeAdapter(val viewModel: MainViewModel, private val type: Boolean) : RecyclerView.Adapter<TopAnimeAdapter.ItemViewHolder>() {

    private val listTopAnime = ArrayList<AnimeEntity>()
    private val listUpcomingAnime = ArrayList<AnimeEntity>()

    fun setTopAnime(list: List<AnimeEntity>) {
        val diffCallback = AnimeCallback(listTopAnime, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listTopAnime.clear()
        listTopAnime.addAll(list)
        diffResult.dispatchUpdatesTo(this@TopAnimeAdapter)
    }

    fun setUpcomingAnime(list: List<AnimeEntity>) {
        val diffCallback = AnimeCallback(listUpcomingAnime, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listUpcomingAnime.clear()
        listUpcomingAnime.addAll(list)
        diffResult.dispatchUpdatesTo(this@TopAnimeAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAnimeTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listUpcomingAnime[position])
    }

    override fun getItemCount(): Int = listTopAnime.size

    inner class ItemViewHolder(private val binding: ItemAnimeTopBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AnimeEntity) {
            with(binding) {
                tvAnimeTitle.text = item.title
                Glide.with(itemView.context)
                    .load(item.imageUrl)
                    .centerCrop()
                    .into(ivAnimeCoverArt)

                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, AddMenuActivity::class.java)
//                    intent.putExtra(AddMenuActivity.EXTRA_MENU, item)
//                    itemView.context.startActivity(intent)
                    Toasty.info(itemView.context, "Anime Title: ${item.title}", Toasty.LENGTH_SHORT).show()
                }
            }
        }
    }
}