package com.auval.newsapp.ui.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.auval.newsapp.R
import com.auval.newsapp.model.pojo.Article
import com.auval.newsapp.model.pojo.NewsApiResponse

class ArticlesListAdapter(
    private val viewModel: MainViewModel,
    private val owner: LifecycleOwner,
    private val context: Context?
) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    private var list = ArrayList<Article>()

    companion object {
        private val TAG = ArticlesListAdapter::class.java.simpleName
    }

    private val newsObserver = Observer<NewsApiResponse?> { news ->
        Log.i(TAG, "got ${news?.totalResults} news articles")
        list.clear()
        list.addAll(news?.articles!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.article_card,
                parent,
                false
            ), viewModel
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindData(list.get(position))
    }

    init {
        viewModel.observeTheNews(owner, newsObserver)
    }

}