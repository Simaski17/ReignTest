package com.jimmyhernandez.reigntest.data.database

import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.domain.Hit
import com.jimmyhernandez.reigntest.data.toDamainNews
import com.jimmyhernandez.reigntest.data.toRoomNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: NewsDatabase) : LocalDataSource {

    private val newsDao = db.newsDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { newsDao. usersCount() <= 0 }

    override suspend fun saveListNews(news: List<Hit>) {
        withContext(Dispatchers.IO) { newsDao.insertNews(news = news.map { it.toRoomNews() }) }
    }

    override suspend fun getAllNews(): List<Hit> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Int): Hit = withContext(Dispatchers.IO) {
        newsDao.findById(id).toDamainNews()
    }

}