package com.jimmyhernandez.reigntest.ui.main

import com.jimmyhernandez.data.repository.ListNewsRepository
import com.jimmyhernandez.usecases.GetCountNewsUseCase
import com.jimmyhernandez.usecases.GetListNewsUseCase
import com.jimmyhernandez.usecases.GetNewsWithoutDeleteUseCase
import com.jimmyhernandez.usecases.UpdateNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainActivityModule(){

    @Provides
    fun mainViewModelProvider(getListNewsUseCase: GetListNewsUseCase, updateNewsUseCase: UpdateNewsUseCase,
                              getCountNewsUseCase: GetCountNewsUseCase, getNewsWithoutDeleteUseCase: GetNewsWithoutDeleteUseCase) = MainViewModel(getListNewsUseCase, updateNewsUseCase, getCountNewsUseCase, getNewsWithoutDeleteUseCase)

    @Provides
    fun getListNewsUseCaseProvider(listNewsRepository: ListNewsRepository) = GetListNewsUseCase(listNewsRepository)

    @Provides
    fun updateNewsUseCaseProvider(listNewsRepository: ListNewsRepository) = UpdateNewsUseCase(listNewsRepository)

    @Provides
    fun getCountNewsUseCaseProvider(listNewsRepository: ListNewsRepository) = GetCountNewsUseCase(listNewsRepository)

    @Provides
    fun getNewsWithoutDeleteUseCaseProvider(listNewsRepository: ListNewsRepository) = GetNewsWithoutDeleteUseCase(listNewsRepository)

}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}