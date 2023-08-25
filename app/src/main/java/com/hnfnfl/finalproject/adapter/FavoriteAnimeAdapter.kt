package com.hnfnfl.finalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnfnfl.finalproject.databinding.ItemAnimeBinding
import com.hnfnfl.finalproject.db.AnimeEntity
import com.hnfnfl.finalproject.repository.AnimeCallback
import com.hnfnfl.finalproject.viewmodel.FavoriteAnimeViewModel
import es.dmoral.toasty.Toasty

class FavoriteAnimeAdapter(val viewModel: FavoriteAnimeViewModel) : RecyclerView.Adapter<FavoriteAnimeAdapter.ItemViewHolder>(), Filterable {

    private var listData = ArrayList<AnimeEntity>()
    private var listDataFilterd = ArrayList<AnimeEntity>()

    fun setList(list: List<AnimeEntity>) {
        val diffCallback = AnimeCallback(listData, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listData.clear()
        listData.addAll(list)
        listDataFilterd = list as ArrayList<AnimeEntity>
        diffResult.dispatchUpdatesTo(this@FavoriteAnimeAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ItemViewHolder(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AnimeEntity) {
            with(binding) {
                btnAddFav.visibility = View.GONE
                val layoutParams = llAnimeRating.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.endToStart = ConstraintLayout.LayoutParams.UNSET
                layoutParams.endToStart = btnDeleteFav.id
                llAnimeRating.layoutParams = layoutParams

                tvAnimeTitle.text = item.title
                tvAnimeStatus.text = item.status
                tvAnimeRating.text = item.rating
                Glide.with(itemView.context).load(item.imageUrl).centerCrop().into(ivAnimeCoverArt)

                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, AddMenuActivity::class.java)
//                    intent.putExtra(AddMenuActivity.EXTRA_MENU, item)
//                    itemView.context.startActivity(intent)
                    Toasty.info(itemView.context, "Anime Title: ${item.title}", Toasty.LENGTH_SHORT).show()
                }

                btnDeleteFav.setOnClickListener {
                    viewModel.deleteFavoriteAnime(item)
                    Toasty.success(itemView.context, "Anime ${item.title} berhasil dihapus dari favorite", Toasty.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(char: CharSequence?): FilterResults {
                val filterResults = FilterResults()

                val resultList = if (char.isNullOrEmpty()) {
                    listDataFilterd.toList()
                } else {
                    val charSearch = char.toString().lowercase()
                    listDataFilterd.filter { row ->
                        row.title?.lowercase()?.contains(charSearch) == true
                    }
                }

                filterResults.count = resultList.size
                filterResults.values = resultList
                return filterResults
            }

            override fun publishResults(char: CharSequence?, filterResults: FilterResults?) {
                listData.clear()
                listData.addAll(filterResults?.values as ArrayList<AnimeEntity>)
                notifyDataSetChanged()
            }
        }
    }
}