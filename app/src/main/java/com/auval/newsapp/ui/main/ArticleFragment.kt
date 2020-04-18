package com.auval.newsapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.auval.newsapp.databinding.ArticleFragmentBinding

private const val URL = "url"

class ArticleFragment : Fragment() {
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL)
        }
    }
    private lateinit var binding: ArticleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArticleFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.loadUrl(url)
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, url)
                }
            }
    }
}
