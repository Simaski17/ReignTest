package com.jimmyhernandez.reigntest.data.database

import androidx.room.*

@Dao
interface NewsResponseDao {

    @Query("SELECT COUNT(story_id) FROM Hit")
    fun newsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(news: List<Hit>)

    @Query("SELECT * FROM hit WHERE created_at_i = :created_at_i")
    fun findById(created_at_i: Int): Hit

    @Update
    fun updateNews(hit: Hit)

    @Query("SELECT * FROM hit WHERE `delete` = :delete ORDER BY LOWER(created_at) DESC")
    fun getNewsWithoutDelete(delete: Boolean): List<Hit>

}