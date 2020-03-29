package com.jimmyhernandez.usecases

import com.jimmyhernandez.data.repository.ListNewsRepository
import com.jimmyhernandez.domain.Hit

class UpdateNewsUseCase(private val listNewsRepository: ListNewsRepository) {
    suspend fun invoke(hit: Hit): Hit = with(hit) {
        copy(delete = !delete).also { listNewsRepository.updateNews(it) }
    }
}