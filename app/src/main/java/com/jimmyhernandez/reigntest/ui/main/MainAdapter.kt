package com.jimmyhernandez.reigntest.ui.main

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimmyhernandez.domain.HackerNewsResponse
import com.jimmyhernandez.domain.Hit
import com.jimmyhernandez.reigntest.R
import com.jimmyhernandez.reigntest.ui.common.JodaTime
import com.jimmyhernandez.yapotest.ui.common.inflate
import kotlinx.android.synthetic.main.item_news_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(private val listener: (Hit) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>()  {

    private var listOfNews = arrayListOf<Hit>()

    fun updateListNews(news: ArrayList<Hit>) {
        this.listOfNews.addAll(news)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_news_view, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listOfNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = listOfNews[position]
        holder.bind(news)

        holder.itemView.setOnClickListener { listener(news) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var jodaTime = JodaTime()

        fun bind(news: Hit) {
            if (news.title != null){
                itemView.tvTitleNews.text = news.title
                itemView.tvSubtitleNews.text = "${news.author} - ${jodaTime.transformDate(news.createdAt)}"
            } else {
                itemView.tvTitleNews.text = news.storyTitle
                itemView.tvSubtitleNews.text = "${news.author} - ${jodaTime.transformDate(news.createdAt)}"
            }

        }

        fun dateFormat(date: String){
            val inPattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
            val outPattern = "dd.MM.yyyy hh:mm"

            val inFormat = SimpleDateFormat(inPattern, Locale.getDefault())
            val outFormat = SimpleDateFormat(outPattern, Locale.getDefault())

            val inDate = inFormat.parse(date)
            val outDate = outFormat.format(inDate)

            Log.e("TEST", outDate)
        }
    }



}