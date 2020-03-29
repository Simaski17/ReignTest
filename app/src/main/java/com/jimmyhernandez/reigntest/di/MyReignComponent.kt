package com.jimmyhernandez.reigntest.di

import android.app.Application
import com.jimmyhernandez.reigntest.ui.detail.DetailActivityComponent
import com.jimmyhernandez.reigntest.ui.detail.DetailActivityModule
import com.jimmyhernandez.reigntest.ui.main.MainActivityComponent
import com.jimmyhernandez.reigntest.ui.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MyReignComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule): DetailActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyReignComponent
    }

}