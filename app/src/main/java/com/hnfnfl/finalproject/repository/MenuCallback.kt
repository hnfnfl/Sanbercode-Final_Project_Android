package com.hnfnfl.finalproject.repository

import androidx.recyclerview.widget.DiffUtil
import com.hnfnfl.finalproject.db.Menu

class MenuCallback(private val oldList: List<Menu>, private val newList: List<Menu>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMenu = oldList[oldItemPosition]
        val newMenu = newList[newItemPosition]
        return oldMenu.name == newMenu.name && oldMenu.description == newMenu.description && oldMenu.price == newMenu.price
    }
}
