package com.jimmyhernandez.reigntest.data.server

import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.domain.HackerNewsResponse

class TheReignDbDatasource(private val theReignDbService: TheReignDbService) : RemoteDataSource {

    override suspend fun gitListNewsResponse(): HackerNewsResponse = theReignDbService.getListUsers("android").execute().body()!!

}