package com.hamzaazman.finalspace.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first

inline fun <T, K> networkBoundResource(
    crossinline query: () -> Flow<T>,
    crossinline fetch: suspend () -> K,
    crossinline saveFetchResult: suspend (K) -> Unit,
    crossinline shouldFetch: (T) -> Boolean = { true }
) = channelFlow<Resource<T>> {
    val data = query().first()
    send(Resource.Loading())

    if (shouldFetch(data)) {
        try {
            saveFetchResult(fetch())
            try {
                query().collect { send(Resource.Success(it)) }
            } catch (e: Exception) {
                send(Resource.Error(e.localizedMessage ?: "Error!", null))
            }
        } catch (e: Exception) {
            try {
                query().collect { send(Resource.Error(e.localizedMessage ?: "Error", it)) }
            } catch (e: Exception) {
                send(Resource.Error(e.localizedMessage ?: "Error!", null))
            }
        }
    } else {
        try {
            query().collect { send(Resource.Success(it)) }
        } catch (e: Exception) {
            send(Resource.Error(e.localizedMessage ?: "Error!", null))
        }
    }
}