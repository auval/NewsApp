package com.auval.newsapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.auval.newsapp.MainApplication
import com.auval.newsapp.databinding.MainFragmentBinding
import com.auval.newsapp.model.pojo.NewsApiResponse

// todo - show credit for newsapi.org
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        private val TAG = MainFragment::class.java.simpleName
    }

//    private lateinit var viewModel: MainViewModel
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
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = ArticlesListAdapter(viewModel, this, context)
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val appContainer = (activity?.application as MainApplication).appContainer

//        viewModel.observeTheNews(this, newsObserver)
        viewModel.fetchTheNews(appContainer.newsModel)
    }

//    val newsObserver = Observer<NewsApiResponse?> { news ->
//        Log.i(TAG, "got ${news?.totalResults} news articles")
//
//    }

}
