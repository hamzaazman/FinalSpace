package com.hamzaazman.finalspace.data.repo

import com.hamzaazman.finalspace.data.database.CharacterDao
import com.hamzaazman.finalspace.data.network.ConnectivityObserver
import com.hamzaazman.finalspace.data.network.SpaceApi
import com.hamzaazman.finalspace.util.fromCharacterToModel
import com.hamzaazman.finalspace.util.networkBoundResource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao,
    private val api: SpaceApi,
    private val networkObserverRepository: ConnectivityObserver,
    ) {
    fun getCharacters() =
        networkBoundResource(
            query = { characterDao.getAll() },
            fetch = {
                api.getAllCharacters()
            },
            saveFetchResult = {
                characterDao.deleteAll()
                val list = it.let { listCharacter ->
                    listCharacter.map { character ->
                        fromCharacterToModel(character)
                    }
                }
                characterDao.insertAll(list)
            },
            shouldFetch = { characters ->
                characters.isEmpty()
            },
            networkObserverRepository
        )
}