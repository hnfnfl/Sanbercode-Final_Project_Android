package com.hnfnfl.finalproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu ORDER BY name ASC")
    fun getAllMenu(): LiveData<List<Menu>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMenu(menu: Menu)

    @Update
    fun updateMenu(menu: Menu)

    @Delete
    fun deleteMenu(menu: Menu)
}