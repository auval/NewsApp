package com.auval.newsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.auval.newsapp.MainApplication
import com.auval.newsapp.R
import com.auval.newsapp.databinding.MainFragmentBinding

// todo - show credit for newsapi.org
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = getViewModel()
        val adapter = ArticlesListAdapter(viewModel, viewLifecycleOwner, context)
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.observeSelectedArticle(this, selectionObserver)
    }

    private fun getViewModel(): MainViewModel {
        val appContainer = (activity?.application as MainApplication).appContainer
        return ViewModelProvider(
            this,
            MainViewModelFactory(appContainer)
        ).get(MainViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = getViewModel()
        viewModel.fetchTheNews()
    }

    private val selectionObserver = Observer<String?> { url ->
        if (url != null) {
            openArticle(url)
        }
    }

    private fun openArticle(url: String) {
        val httpsUrl = url.replace("http://", "https://")
        val fragment = ArticleFragment.newInstance(httpsUrl)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.commit()
    }
}
