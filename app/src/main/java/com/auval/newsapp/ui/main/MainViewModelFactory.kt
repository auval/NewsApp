package com.auval.newsapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.auval.newsapp.DIContainer

class MainViewModelFactory(private val model: DIContainer): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(model) as T
}