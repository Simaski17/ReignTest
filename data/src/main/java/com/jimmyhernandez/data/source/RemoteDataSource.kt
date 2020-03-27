package com.jimmyhernandez.data.source

import com.jimmyhernandez.domain.HackerNewsResponse

interface RemoteDataSource {

    suspend fun gitListNewsResponse(): HackerNewsResponse

}