package com.hnfnfl.finalproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    companion object {
        @Volatile
        private var INSTANCE: AnimeDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AnimeDatabase {
            if (INSTANCE == null) {
                synchronized(AnimeDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, AnimeDatabase::class.java, "anime_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}