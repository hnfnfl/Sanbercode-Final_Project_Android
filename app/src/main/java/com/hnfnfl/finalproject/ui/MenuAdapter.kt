package com.hnfnfl.finalproject.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hnfnfl.finalproject.R
import com.hnfnfl.finalproject.databinding.ItemMenuRestoBinding
import com.hnfnfl.finalproject.db.Menu
import com.hnfnfl.finalproject.repository.MenuCallback
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class MenuAdapter(val viewModel: MainViewModel) : RecyclerView.Adapter<MenuAdapter.ItemViewHolder>() {

    private val listItem = ArrayList<Menu>()

    fun setList(list: List<Menu>) {
        val diffCallback = MenuCallback(listItem, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listItem.clear()
        listItem.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemMenuRestoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(private val binding: ItemMenuRestoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Menu) {
            with(binding) {
                tvMenuName.text = item.name
                tvMenuDesc.text = item.description
                val formatter = DecimalFormat("#,###", DecimalFormatSymbols.getInstance(Locale("en", "US")))
                val formattedNumber = formatter.format(item.price)
                tvMenuPrice.text = itemView.context.getString(R.string.price, formattedNumber)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, AddMenuActivity::class.java)
                    intent.putExtra(AddMenuActivity.EXTRA_MENU, item)
                    itemView.context.startActivity(intent)
                }
                btnDeleteMenu.setOnClickListener {
                    viewModel.deleteMenu(item)
                }
            }
        }
    }
}