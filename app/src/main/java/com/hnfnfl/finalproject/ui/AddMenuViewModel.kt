package com.hnfnfl.finalproject.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import com.hnfnfl.finalproject.db.Menu
import com.hnfnfl.finalproject.repository.MenuRepository

class AddMenuViewModel(application: Application) : ViewModel() {

    private val repository: MenuRepository = MenuRepository(application)

    fun insertMenu(menu: Menu) = repository.insertMenu(menu)

    fun updateMenu(menu: Menu) = repository.updateMenu(menu)
}