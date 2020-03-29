package com.jimmyhernandez.data.source

import com.jimmyhernandez.domain.Hit

interface LocalDataSource {

    suspend fun isEmpty(): Boolean
    suspend fun saveListNews(news: List<Hit>)
    suspend fun getAllNews(): List<Hit>
    suspend fun findById(id: Int): Hit

}