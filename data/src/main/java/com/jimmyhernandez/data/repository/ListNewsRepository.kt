package com.jimmyhernandez.data.repository

import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.domain.HackerNewsResponse
import com.jimmyhernandez.domain.Hit

class ListNewsRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

    suspend fun getListNews(): HackerNewsResponse {
        var news = remoteDataSource.gitListNewsResponse()
        localDataSource.saveListNews((news.hits as List<Hit>?)!!)
        return news
    }

    suspend fun findById(id: Int): Hit = localDataSource.findById(id)

}