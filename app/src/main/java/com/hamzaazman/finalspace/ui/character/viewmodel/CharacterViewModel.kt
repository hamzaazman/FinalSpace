package com.hamzaazman.finalspace.ui.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hamzaazman.finalspace.data.repo.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    characterRepository: CharacterRepository
) : ViewModel() {

    val characters = characterRepository.getCharacters().asLiveData()

}