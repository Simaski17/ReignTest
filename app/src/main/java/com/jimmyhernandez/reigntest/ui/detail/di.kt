package com.jimmyhernandez.reigntest.ui.detail

import com.jimmyhernandez.data.repository.ListNewsRepository
import com.jimmyhernandez.usecases.FindNewsById
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailActivityModule(private val id: Int) {

    @Provides
    fun detailViewModelProvider(findNewsById: FindNewsById): DetailViewModel {
        return DetailViewModel(id, findNewsById)
    }

    @Provides
    fun gettNewsFindId(listNewsRepository: ListNewsRepository) = FindNewsById(listNewsRepository)

}

@Subcomponent(modules = [DetailActivityModule::class])
interface DetailActivityComponent {
    val detailViewModel: DetailViewModel
}