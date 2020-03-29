package com.jimmyhernandez.reigntest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Hit::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsResponseDao

}