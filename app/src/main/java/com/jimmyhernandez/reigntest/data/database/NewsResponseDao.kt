package com.jimmyhernandez.reigntest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsResponseDao {

    @Query("SELECT COUNT(story_id) FROM Hit")
    fun usersCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<Hit>)

    @Query("SELECT * FROM hit WHERE created_at_i = :created_at_i")
    fun findById(created_at_i: Int): Hit

}