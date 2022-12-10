package com.hamzaazman.finalspace.ui.location.viewmodel

import androidx.lifecycle.*
import com.hamzaazman.finalspace.data.database.LocationDao
import com.hamzaazman.finalspace.data.network.ConnectivityObserver
import com.hamzaazman.finalspace.data.repo.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    locationRepository: LocationRepository
) : ViewModel() {

    val locations = locationRepository.getLocations().asLiveData()


}