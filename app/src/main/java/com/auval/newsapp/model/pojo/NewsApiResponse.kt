package com.auval.newsapp.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsApiResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null

    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null

}