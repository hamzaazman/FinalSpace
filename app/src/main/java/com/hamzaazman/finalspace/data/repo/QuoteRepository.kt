package com.hamzaazman.finalspace.data.repo

import com.hamzaazman.finalspace.data.database.QuoteDao
import com.hamzaazman.finalspace.data.network.ConnectivityObserver
import com.hamzaazman.finalspace.data.network.SpaceApi
import com.hamzaazman.finalspace.util.fromQuoteToModel
import com.hamzaazman.finalspace.util.networkBoundResource
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteDao: QuoteDao,
    private val api: SpaceApi,
    private val networkObserverRepository: ConnectivityObserver,
    ) {
    fun getAllQuote() = networkBoundResource(
        query = { quoteDao.getAll() },
        fetch = { api.getAllQuote() },
        saveFetchResult = {
            quoteDao.deleteAll()
            val list = it.let { quotes ->
                quotes.map { quote -> fromQuoteToModel(quote) }
            }
            quoteDao.insertAll(list)
        },
        shouldFetch = { quotes ->
            quotes.isEmpty()
        },
        networkObserverRepository
    )

}