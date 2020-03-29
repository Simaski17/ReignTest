package com.jimmyhernandez.reigntest.di

import com.jimmyhernandez.data.repository.ListNewsRepository
import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun listNewsRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource) = ListNewsRepository(localDataSource, remoteDataSource)

}