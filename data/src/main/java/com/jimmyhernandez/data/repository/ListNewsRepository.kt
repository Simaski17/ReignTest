package com.jimmyhernandez.data.repository

import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.domain.Hit

class ListNewsRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

    suspend fun getListNews(): List<Hit> {
        var news = remoteDataSource.gitListNewsResponse().hits
        localDataSource.saveListNews((news as List<Hit>?)!!)
        return localDataSource.getNewsWithoutDelete(true)
    }

    suspend fun findById(id: Int): Hit = localDataSource.findById(id)

    suspend fun updateNews(news: Hit) = localDataSource.updateNews(news)

    suspend fun getCount(): Boolean {
        return localDataSource.isEmpty()
    }

    suspend fun getNewsWithoutDelete(): List<Hit> {
        return localDataSource.getNewsWithoutDelete(true)
    }

}