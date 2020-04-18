package com.auval.newsapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.auval.newsapp.ui.main.ArticleFragment
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
        val viewModel = getViewModel()
        supportActionBar?.subtitle = getString(R.string.credit)
        viewModel.observeSelectedArticle(this, Observer {
            if (it == null) {
                // navigate to and refresh the main screen
                openArticlesList()
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                openArticle(it)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            val appContainer = (application as MainApplication).appContainer
            appContainer.newsModel.selectedArticleUrl.postValue(null)
        }
        return super.onOptionsItemSelected(menuItem)
    }

    private fun openArticle(url: String) {
        val httpsUrl = url.replace("http://", "https://")
        val fragment = ArticleFragment.newInstance(httpsUrl)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
