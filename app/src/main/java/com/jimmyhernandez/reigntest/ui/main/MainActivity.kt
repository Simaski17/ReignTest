package com.jimmyhernandez.reigntest.ui.main

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.domain.HackerNewsResponse
import com.jimmyhernandez.reigntest.R
import com.jimmyhernandez.reigntest.ui.common.JodaTime
import com.jimmyhernandez.reigntest.ui.detail.DetailActivity
import com.jimmyhernandez.yapotest.ui.common.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }
    private var viewIfl: View? = null
    private var mNetworkReceiver = ConnectivityReceiver()

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = app.component.plus(MainActivityModule())
        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.gitListNewsResponse()

        rrvMainNews.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

            mainAdapter = MainAdapter {
                startActivity<DetailActivity>{
                    putExtra(DetailActivity.NEWS, it.createdAtI)
                }
            }

            rrvMainNews.adapter = mainAdapter
        }

    }

    private fun updateUi(event: Data<HackerNewsResponse>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                    pbMainNews.visibility = VISIBLE
                    tvMainNews.visibility = VISIBLE
                }
                DataState.SUCCESS -> {
                    pbMainNews.visibility = GONE
                    tvMainNews.visibility = GONE
                }
                DataState.ERROR -> {
                    pbMainNews.visibility = GONE
                    tvMainNews.visibility = GONE
                }
            }

            data.notNull {
                var lista = ArrayList(it.hits)
                mainAdapter.updateListNews(lista)
            }

        }
    }

}
