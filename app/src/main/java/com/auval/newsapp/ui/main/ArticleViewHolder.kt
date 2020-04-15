package com.auval.newsapp.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.auval.newsapp.databinding.ArticleCardBinding
import com.auval.newsapp.model.pojo.Article

class ArticleViewHolder(
    private val binding: ArticleCardBinding,
    private val viewModel: MainViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(article: Article) {
        binding.article = article
        binding.viewModel = viewModel
    }
}
