package com.auval.newsapp.ui.main

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.auval.newsapp.model.NewsModel
import com.auval.newsapp.model.pojo.NewsApiResponse

class MainViewModel : ViewModel() {
    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    val data: MutableLiveData<NewsApiResponse?> by lazy {
        MutableLiveData<NewsApiResponse?>()
    }

    fun observeTheNews(owner: LifecycleOwner ,observer: Observer<NewsApiResponse?>) {
        data.observe(owner, observer)
    }

    fun fetchTheNews(newsModel: NewsModel) {
        newsModel.fetchTheNews("cnn", data)
    }

    fun onArticleSelected(articleUrl: String) {
        Log.i(TAG, "article selected: $articleUrl")
        // show spinner
        // fetch article
        // show article
    }
}
