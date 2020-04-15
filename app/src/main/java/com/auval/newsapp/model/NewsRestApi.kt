package com.auval.newsapp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.auval.newsapp.model.pojo.NewsApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

class NewsRestApi {
    private val mService: INewsRestApi

    interface INewsRestApi {
        @Headers("Accept: application/json")
        @GET("top-headlines")
        fun getTheNews(
            @Query("sources") source: String?,
            @Query("apiKey") apiKey: String?
        ): Call<NewsApiResponse?>
    }

    fun getNewsFromWeb(
        source: String?,
        data: MutableLiveData<NewsApiResponse?>
    ) {
        mService.getTheNews(source, API_KEY)
            .enqueue(object : Callback<NewsApiResponse?> {
                override fun onResponse(
                    call: Call<NewsApiResponse?>,
                    response: Response<NewsApiResponse?>
                ) {
                    Log.e(
                        TAG, "getNewsFromWeb() succeeded! " + response.body()!!.totalResults
                    )
                    data.postValue(response.body())
                }

                override fun onFailure(
                    call: Call<NewsApiResponse?>,
                    t: Throwable
                ) {
                    Log.e(TAG, "getNewsFromWeb() failed", t)
                    // out of scope of coding challenge
                }
            })
    }

    companion object {
        private val TAG = NewsRestApi::class.java.simpleName
        private const val NEWS_API_URL = "https://newsapi.org/v2/"
        private const val API_KEY = "b9c2180244df45a9b4da3114359ac041"
    }

    init {
        val retrofit = Retrofit.Builder().baseUrl(NEWS_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mService = retrofit.create(INewsRestApi::class.java)
    }
}