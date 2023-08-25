package com.hnfnfl.finalproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnfnfl.finalproject.databinding.ItemAnimeTopBinding
import com.hnfnfl.finalproject.db.AnimeEntity
import com.hnfnfl.finalproject.repository.AnimeCallback
import es.dmoral.toasty.Toasty

class UpcomingAnimeAdapter() : RecyclerView.Adapter<UpcomingAnimeAdapter.ItemViewHolder>() {

    private val listData = ArrayList<AnimeEntity>()

    fun setList(list: List<AnimeEntity>) {
        val diffCallback = AnimeCallback(listData, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listData.clear()
        listData.addAll(list)
        diffResult.dispatchUpdatesTo(this@UpcomingAnimeAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAnimeTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

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