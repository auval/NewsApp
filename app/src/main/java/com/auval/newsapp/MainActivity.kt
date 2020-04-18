package com.auval.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.auval.newsapp.ui.main.MainFragment
import com.auval.newsapp.ui.main.MainViewModel
import com.auval.newsapp.ui.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            openArticlesList()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val viewModel = getViewModel()
        viewModel.observeSelectedArticle(this, Observer {
            if (it == null) {
                // navigate to and refresh the main screen
                openArticlesList()
                viewModel.fetchTheNews()
            }
        })
    }

    private fun openArticlesList() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
    }

    private fun getViewModel(): MainViewModel {
        val appContainer = (application as MainApplication).appContainer
        return ViewModelProvider(this, MainViewModelFactory(appContainer)).get(MainViewModel::class.java)
    }

    override fun onBackPressed() {
        val appContainer = (application as MainApplication).appContainer
        if (appContainer.newsModel.selectedArticleUrl.value == null) {
            super.onBackPressed()
        } else {
            appContainer.newsModel.selectedArticleUrl.postValue(null)
        }

    }
}
