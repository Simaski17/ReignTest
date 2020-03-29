package com.jimmyhernandez.usecases

import com.jimmyhernandez.data.repository.ListNewsRepository
import com.jimmyhernandez.domain.Hit

class FindNewsById(private val newsRepository: ListNewsRepository) {

    suspend fun invoke(news: Int): Hit = newsRepository.findById(news)

}