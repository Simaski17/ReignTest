package com.jimmyhernandez.usecases

import com.jimmyhernandez.data.repository.ListNewsRepository

class GetCountNewsUseCase(private val listNewsRepository: ListNewsRepository) {

    suspend fun invoke(): Boolean = listNewsRepository.getCount()

}