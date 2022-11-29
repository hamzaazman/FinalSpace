package com.hamzaazman.finalspace.ui.episode

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.hamzaazman.finalspace.R
import com.hamzaazman.finalspace.databinding.FragmentEpisodeDetailBinding
import com.hamzaazman.finalspace.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment(R.layout.fragment_episode_detail) {
    private val binding by viewBinding(FragmentEpisodeDetailBinding::bind)

    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val episode = args.episode

        with(binding) {
            episodeDetailName.text = episode.name
            episodeDetailWriter.text = episode.writer
            episodeDetailDirector.text = episode.director
            episodeDetailAirDate.text = episode.air_date

            episodeDetailImage.load(episode.img_url) {
                crossfade(true)
            }
        }
    }
}