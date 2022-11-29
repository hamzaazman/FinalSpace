package com.hamzaazman.finalspace.data.repo

import com.hamzaazman.finalspace.data.database.EpisodeDao
import com.hamzaazman.finalspace.data.network.SpaceApi
import com.hamzaazman.finalspace.util.fromEpisodeToModel
import com.hamzaazman.finalspace.util.networkBoundResource
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val episodeDao: EpisodeDao,
    private val api: SpaceApi
) {
    fun getAllEpisode() = networkBoundResource(
        query = { episodeDao.getAll() },
        fetch = { api.getAllEpisode() },
        saveFetchResult = {
            episodeDao.deleteAll()
            val list = it.body()?.let { episodes ->
                episodes.map { episode -> fromEpisodeToModel(episode) }
            }
            episodeDao.insertAll(list!!)
        },
        shouldFetch = {
            it.isEmpty()
        }
    )


}