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
    }

    private fun getViewModel(): MainViewModel {
        val appContainer = (activity?.application as MainApplication).appContainer
        return ViewModelProvider(
            this,
            MainViewModelFactory(appContainer)
        ).get(MainViewModel::class.java)
    }

    /**
     * Requirement fulfilled - the refresh will be called upon:
     * (1) app start
     * (2) resuming from background state
     * (3) returning to the main screen from an article
     */
    override fun onResume() {
        super.onResume()
        val viewModel = getViewModel()
        viewModel.fetchTheNews()
    }
}
