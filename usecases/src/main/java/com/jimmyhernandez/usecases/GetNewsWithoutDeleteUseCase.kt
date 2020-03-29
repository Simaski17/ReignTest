package com.jimmyhernandez.usecases

import com.jimmyhernandez.data.repository.ListNewsRepository
import com.jimmyhernandez.domain.Hit

class GetNewsWithoutDeleteUseCase(private val listNewsRepository: ListNewsRepository) {

    suspend fun invoke(): List<Hit> = listNewsRepository.getNewsWithoutDelete()

}