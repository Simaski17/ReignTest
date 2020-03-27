package com.jimmyhernandez.data.repository

import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.domain.HackerNewsResponse

class ListNewsRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getListNews(): HackerNewsResponse {
        return remoteDataSource.gitListNewsResponse()
    }

}