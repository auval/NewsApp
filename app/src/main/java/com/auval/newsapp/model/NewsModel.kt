package com.auval.newsapp.model

import androidx.lifecycle.MutableLiveData
import com.auval.newsapp.model.pojo.NewsApiResponse

class NewsModel {

    val source = "cnn"

    private val webservice = NewsRestApi()

    val selectedArticleUrl: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }

    fun fetchTheNews(
        source: String,
        data: MutableLiveData<NewsApiResponse?>
    ) {
        webservice.getNewsFromWeb(source, data)
    }


}