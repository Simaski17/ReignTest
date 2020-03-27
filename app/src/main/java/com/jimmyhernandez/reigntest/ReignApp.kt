package com.jimmyhernandez.reigntest

import android.app.Application
import com.jimmyhernandez.reigntest.di.DaggerMyReignComponent
import com.jimmyhernandez.reigntest.di.MyReignComponent

class ReignApp : Application() {

    lateinit var component: MyReignComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerMyReignComponent.factory().create(this)

    }

}