package com.jimmyhernandez.reigntest.ui.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.domain.Hit
import com.jimmyhernandez.reigntest.R
import com.jimmyhernandez.reigntest.ui.common.SwipeToDeleteCallback
import com.jimmyhernandez.reigntest.ui.detail.DetailActivity
import com.jimmyhernandez.yapotest.ui.common.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }

    private var isConnectedNet: Boolean = true
    private var viewIfl: View? = null
    private var mNetworkReceiver = ConnectivityReceiver()

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = app.component.plus(MainActivityModule())
        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.modelDelete.observe(this, Observer(::getNewsDeleteResponse))
        viewModel.modelCount.observe(this, Observer(::getCount))
        viewModel.getCount()

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

        val swipeHandler = object : SwipeToDeleteCallback(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rrvMainNews.adapter as MainAdapter
                adapter.removeItem(viewHolder.adapterPosition, viewHolder)
                viewModel.onDeleteClicked(adapter.removedItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rrvMainNews)

        srlMainNews.setOnRefreshListener {
            if (isConnectedNet) {
                viewModel.gitListNewsResponse()
            } else {
                srlMainNews.isRefreshing = false
            }
        }

    }

    private fun updateUi(event: Data<List<Hit>>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                    pbMainNews.visibility = View.VISIBLE
                    tvMainNews.visibility = View.VISIBLE
                    tvNetErrorIsEmpty.visibility = View.GONE
                }
                DataState.SUCCESS -> {
                    pbMainNews.visibility = View.GONE
                    tvMainNews.visibility = View.GONE
                    srlMainNews.isRefreshing = false
                    tvNetErrorIsEmpty.visibility = View.GONE
                }
                DataState.ERROR -> {
                    pbMainNews.visibility = View.GONE
                    tvMainNews.visibility = View.GONE
                }
            }

            data.notNull {
                var lista = ArrayList(it)
                mainAdapter.updateListNews(lista)
            }

        }
    }

    private fun getNewsDeleteResponse(event: Data<Hit>?){

        event.with {
            when (dataState) {
                DataState.LOADING -> {}
                DataState.SUCCESS -> {}
                DataState.ERROR -> {}
            }

            data.notNull {
                Log.e("getNewsDeleteResponse","RESPONSE $it")
            }

        }
    }
    private fun getCount(event: Data<Boolean>) {
        event.with {
            when (dataState) {
                DataState.LOADING -> {}
                DataState.SUCCESS -> {}
                DataState.ERROR -> {}
            }

            data.notNull {
                if (it) {//0
                        if(isConnectedNet) {
                            Log.e("isConnectedNet","CONECTADO M")
                            viewModel.gitListNewsResponse()
                        }else {
                            tvNetErrorIsEmpty.visibility = View.VISIBLE
                            Log.e("isConnectedNet","NO CONECTADO M")
                        }
                } else {//>0
                    if(isConnectedNet) {
                        Log.e("isConnectedNet","CONECTADO")
                        viewModel.gitListNewsResponse()
                    } else {
                        Log.e("isConnectedNet","NO CONECTADO")
                        viewModel.getNewsWithoutDelete()
                    }
                }
            }
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        isConnectedNet = isConnected
        if (!isConnected) {
            if (viewIfl == null) {
                viewIfl = View(applicationContext)
                viewIfl = vsNetworkingNotAvailableMain.inflate()
            } else if (viewIfl != null) {
                viewIfl!!.visibility = View.VISIBLE
            }
        } else {
            viewIfl?.let {
                viewIfl!!.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            mNetworkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()
        viewIfl?.let {
            viewIfl!!.visibility = View.GONE
        }
        unregisterReceiver(mNetworkReceiver)
    }

}
