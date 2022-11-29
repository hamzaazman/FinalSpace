package com.hamzaazman.finalspace.ui.location.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzaazman.finalspace.data.repo.LocationRepository
import com.hamzaazman.finalspace.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {

    val getCharactersByResidentResponse: MutableLiveData<Character> =
        MutableLiveData()

    fun getCharactersByLocation(url: String) = viewModelScope.launch {
        val response = locationRepository.getCharactersByLocation(url)
        getCharactersByResidentResponse.value = response.body()
    }


}