package com.jimmyhernandez.reigntest.ui.detail

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.*
import androidx.lifecycle.Observer
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.domain.Hit
import com.jimmyhernandez.reigntest.R
import com.jimmyhernandez.yapotest.ui.common.app
import com.jimmyhernandez.yapotest.ui.common.getViewModel
import com.jimmyhernandez.yapotest.ui.common.notNull
import com.jimmyhernandez.yapotest.ui.common.with
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val NEWS = "DetailActivity:news"
    }

    private lateinit var component: DetailActivityComponent
    private val viewModel: DetailViewModel by lazy { getViewModel { component.detailViewModel } }

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

}
