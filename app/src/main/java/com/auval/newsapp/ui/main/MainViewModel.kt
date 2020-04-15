package com.auval.newsapp.ui.main

import androidx.lifecycle.ViewModel
import com.auval.newsapp.model.NewsModel

class MainViewModel : ViewModel() {

    fun fetchTheNews(newsModel: NewsModel) {
        newsModel.fetchTheNews("cnn")
    }
}
