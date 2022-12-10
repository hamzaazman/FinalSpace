package com.hamzaazman.finalspace.ui.quote

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hamzaazman.finalspace.R
import com.hamzaazman.finalspace.databinding.FragmentQuoteBinding
import com.hamzaazman.finalspace.ui.quote.adapter.QuoteAdapter
import com.hamzaazman.finalspace.ui.quote.viewmodel.QuoteViewModel
import com.hamzaazman.finalspace.util.Resource
import com.hamzaazman.finalspace.util.connectivitySnackBar
import com.hamzaazman.finalspace.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteFragment : Fragment(R.layout.fragment_quote) {
    private val binding by viewBinding(FragmentQuoteBinding::bind)

    private lateinit var quoteAdapter: QuoteAdapter
    private val quoteViewModel: QuoteViewModel by viewModels()
    private lateinit var connectivitySnackBar: Snackbar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Connectivity Warning SnackBar
        connectivitySnackBar = connectivitySnackBar(requireView()) {
            observeQuoteData()
        }

        observeQuoteData()
        with(binding) {
            quoteRecyclerView.apply {
                quoteAdapter = QuoteAdapter()
                layoutManager = LinearLayoutManager(requireContext())
                adapter = quoteAdapter
                clipToPadding = false
            }
        }
    }

    private fun observeQuoteData() {
        with(binding) {
            quoteViewModel.quotes.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Error -> {
                        if (resource.message == "networkError") {
                            connectivitySnackBar.show()
                        } else {
                            progressBar.isGone = true
                            quoteRecyclerView.isGone = true
                            Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                        quoteRecyclerView.isGone = true
                    }
                    is Resource.Success -> {
                        connectivitySnackBar.dismiss()
                        progressBar.isGone = true
                        quoteRecyclerView.isVisible = true
                        resource.data?.let { quotes ->
                            quoteAdapter.differ.submitList(quotes)
                        }
                    }
                }
            }
        }
    }
}