package com.hnfnfl.finalproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnfnfl.finalproject.databinding.ItemAnimeBinding
import com.hnfnfl.finalproject.db.AnimeEntity
import com.hnfnfl.finalproject.repository.AnimeCallback
import com.hnfnfl.finalproject.viewmodel.AnimeListViewModel

class AnimeListAdapter(val viewModel: AnimeListViewModel) : RecyclerView.Adapter<AnimeListAdapter.ItemViewHolder>() {

    private val listItem = ArrayList<AnimeEntity>()

    fun setList(list: List<AnimeEntity>) {
        val diffCallback = AnimeCallback(listItem, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listItem.clear()
        listItem.addAll(list)
        diffResult.dispatchUpdatesTo(this@AnimeListAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AnimeEntity) {
            with(binding) {
                tvAnimeTitle.text = item.title
                tvAnimeStatus.text = item.status
                tvAnimeRating.text = item.rating
                Glide.with(itemView.context)
                    .load(item.imageUrl)
                    .centerCrop()
                    .into(ivAnimeCoverArt)
                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, AddMenuActivity::class.java)
//                    intent.putExtra(AddMenuActivity.EXTRA_MENU, item)
//                    itemView.context.startActivity(intent)
                }
                btnAddFav.setOnClickListener {
//                    viewModel.addFavoriteAnime(item)
                }
            }
        }
    }
}