package com.jimmyhernandez.reigntest.ui.main

import com.jimmyhernandez.data.repository.ListNewsRepository
import com.jimmyhernandez.usecases.GetListNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainActivityModule(){

    @Provides
    fun mainViewModelProvider(getListNewsUseCase: GetListNewsUseCase) = MainViewModel(getListNewsUseCase)

    @Provides
    fun getListNewsUseCaseProvider(listNewsRepository: ListNewsRepository) = GetListNewsUseCase(listNewsRepository)

}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}