package com.auval.newsapp.ui.main

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.auval.newsapp.DIContainer
import com.auval.newsapp.model.pojo.NewsApiResponse

/**
 * The constructor parameter is used for manual dependency injection
 */
class MainViewModel(val model: DIContainer) : ViewModel() {

    private val data: MutableLiveData<NewsApiResponse?> by lazy {
        MutableLiveData<NewsApiResponse?>()
    }

    fun observeTheNews(owner: LifecycleOwner ,observer: Observer<NewsApiResponse?>) {
        data.observe(owner, observer)
    }

    fun observeSelectedArticle(owner: LifecycleOwner ,observer: Observer<String?>) {
        model.newsModel.selectedArticleUrl.observe(owner, observer)
    }

    fun fetchTheNews() {
        model.newsModel.fetchTheNews(model.newsModel.source, data)
    }

    fun onArticleSelected(articleUrl: String) {
        model.newsModel.selectedArticleUrl.postValue(articleUrl)
    }
}
