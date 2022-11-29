package com.hamzaazman.finalspace.ui.episode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hamzaazman.finalspace.data.repo.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    episodeRepository: EpisodeRepository
) : ViewModel() {

    val episodes = episodeRepository.getAllEpisode().asLiveData()

}