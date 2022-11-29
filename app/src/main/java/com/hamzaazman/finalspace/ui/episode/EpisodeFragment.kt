package com.hamzaazman.finalspace.ui.episode

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hamzaazman.finalspace.R
import com.hamzaazman.finalspace.databinding.FragmentEpisodeBinding
import com.hamzaazman.finalspace.model.Episode
import com.hamzaazman.finalspace.ui.episode.adapter.EpisodeAdapter
import com.hamzaazman.finalspace.ui.episode.viewmodel.EpisodeViewModel
import com.hamzaazman.finalspace.util.Resource
import com.hamzaazman.finalspace.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : Fragment(R.layout.fragment_episode), EpisodeAdapter.OnItemClickListener {
    private val binding by viewBinding(FragmentEpisodeBinding::bind)

    private val episodeViewModel: EpisodeViewModel by viewModels()
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        observeEpisodeData()
    }

    private fun observeEpisodeData() {
        episodeViewModel.episodes.observe(viewLifecycleOwner, Observer { resource ->
            with(binding) {
                when (resource) {
                    is Resource.Error -> {
                        recyclerView.isGone = true
                        Toast.makeText(
                            requireContext(), resource.message.toString(), Toast.LENGTH_LONG
                        ).show()
                    }
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                        recyclerView.isGone = true
                    }
                    is Resource.Success -> {
                        progressBar.isGone = true
                        recyclerView.isVisible = true
                        resource.data.let {
                            episodeAdapter.submitList(it)
                        }
                    }
                }
            }
        })
    }

    private fun setupRv() {
        binding.recyclerView.apply {
            episodeAdapter = EpisodeAdapter(this@EpisodeFragment)
            adapter = episodeAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    override fun onEpisodeClick(episode: Episode) {
        val action = EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(episode)
        findNavController().navigate(action)
    }
}