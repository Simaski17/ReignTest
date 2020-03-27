package com.jimmyhernandez.usecases

import com.jimmyhernandez.data.repository.ListNewsRepository
import com.jimmyhernandez.domain.HackerNewsResponse

class GetListNewsUseCase(private val listNewsRepository: ListNewsRepository) {

    suspend fun invoke(): HackerNewsResponse = listNewsRepository.getListNews()

}