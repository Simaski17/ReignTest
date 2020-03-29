package com.jimmyhernandez.reigntest.ui.detail

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.*
import androidx.lifecycle.Observer
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.domain.Hit
import com.jimmyhernandez.reigntest.R
import com.jimmyhernandez.yapotest.ui.common.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    companion object {
        const val NEWS = "DetailActivity:news"
    }

    private lateinit var component: DetailActivityComponent
    private val viewModel: DetailViewModel by lazy { getViewModel { component.detailViewModel } }

    private var isConnectedNet: Boolean = false
    private var viewIfl: View? = null
    private var mNetworkReceiver = ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        component = app.component.plus(DetailActivityModule(intent.getIntExtra(NEWS, -1)))

        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.findNews()

    }

    private fun updateUi(event: Data<Hit>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                    pbNewsDetail.visibility = VISIBLE
                    tvNewsDetail.visibility = VISIBLE
                }
                DataState.SUCCESS -> {
                    wbDetailNews.visibility = VISIBLE
                }
                DataState.ERROR -> {
                    pbNewsDetail.visibility = View.GONE
                    tvNewsDetail.visibility = GONE
                }
            }

            data.notNull {
                if (it.url != null) {
                    showUrl(it.url.toString())
                } else if (it.storyUrl != null) {
                    showUrl(it.storyUrl.toString())
                } else {
                    wbDetailNews.visibility = GONE
                    pbNewsDetail.visibility = View.GONE
                    tvNewsDetail.visibility = GONE
                    tvErrorDetailNews.visibility = VISIBLE
                }
            }
        }
    }

    private fun showUrl(url: String) {
        with(wbDetailNews) {
            settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {

                override fun doUpdateVisitedHistory(
                    view: WebView?,
                    url: String?,
                    isReload: Boolean
                ) {
                    super.doUpdateVisitedHistory(view, url, isReload)
                    pbNewsDetail.visibility = GONE
                    tvNewsDetail.visibility = GONE
                }
            }
            wbDetailNews.loadUrl(url)
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        isConnectedNet = isConnected
        if (!isConnected) {
            if (viewIfl == null) {
                viewIfl = View(applicationContext)
                viewIfl = vsNetworkingNotAvailableDetail.inflate()
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
