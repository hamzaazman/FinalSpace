package com.hamzaazman.finalspace.ui.quote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hamzaazman.finalspace.data.repo.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    val quotes = quoteRepository.getAllQuote().asLiveData()
}