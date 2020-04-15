package com.auval.newsapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.auval.newsapp.model.pojo.NewsApiResponse

class NewsModel {

    private val webservice = NewsRestApi()

    fun fetchTheNews(source: String): LiveData<NewsApiResponse?> {
        val data = MutableLiveData<NewsApiResponse?>()
        webservice.getNewsFromWeb(source, data)
        return data
    }


}