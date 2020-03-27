package com.jimmyhernandez.reigntest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.domain.HackerNewsResponse
import com.jimmyhernandez.reigntest.R
import com.jimmyhernandez.yapotest.ui.common.ConnectivityReceiver
import com.jimmyhernandez.yapotest.ui.common.app
import com.jimmyhernandez.yapotest.ui.common.getViewModel
import com.jimmyhernandez.yapotest.ui.common.with

class MainActivity : AppCompatActivity() {

    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }
    private var viewIfl: View? = null
    private var mNetworkReceiver = ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = app.component.plus(MainActivityModule())
        viewModel.gitListNewsResponse()

    }

    private fun updateUi(event: Data<HackerNewsResponse>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                }
                DataState.SUCCESS -> {
                }
                DataState.ERROR -> {
                }
            }

        }
    }

}
