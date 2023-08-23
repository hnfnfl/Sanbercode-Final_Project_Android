package com.hnfnfl.finalproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Menu::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao

    companion object {
        @Volatile
        private var INSTANCE: MenuDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MenuDatabase {
            if (INSTANCE == null) {
                synchronized(MenuDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, MenuDatabase::class.java, "example_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}