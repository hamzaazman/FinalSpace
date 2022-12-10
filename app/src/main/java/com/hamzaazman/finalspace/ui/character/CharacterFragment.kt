package com.hamzaazman.finalspace.ui.character

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
import com.google.android.material.snackbar.Snackbar
import com.hamzaazman.finalspace.R
import com.hamzaazman.finalspace.databinding.FragmentCharacterBinding
import com.hamzaazman.finalspace.model.Character
import com.hamzaazman.finalspace.ui.character.adapter.CharacterAdapter
import com.hamzaazman.finalspace.ui.character.viewmodel.CharacterViewModel
import com.hamzaazman.finalspace.util.Resource
import com.hamzaazman.finalspace.util.connectivitySnackBar
import com.hamzaazman.finalspace.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character),
    CharacterAdapter.OnItemClickListener {

    private val binding by viewBinding(FragmentCharacterBinding::bind)

    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var connectivitySnackBar: Snackbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Connectivity Warning SnackBar
        connectivitySnackBar = connectivitySnackBar(requireView()) {
            observeCharacterData()
        }

        setupRv()
        observeCharacterData()
    }

    private fun observeCharacterData() {
        characterViewModel.characters.observe(viewLifecycleOwner) { resource ->
            with(binding) {
                when (resource) {
                    is Resource.Error -> {
                        recyclerView.isGone = true
                        if (resource.message == "networkError") {
                            connectivitySnackBar.show()
                        } else {
                            Toast.makeText(
                                requireContext(), resource.message.toString(), Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    is Resource.Loading -> {
                        recyclerView.isGone = true
                        progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        connectivitySnackBar.dismiss()
                        progressBar.isGone = true
                        resource.data.let { characterAdapter.submitList(it) }
                        recyclerView.isVisible = true
                    }
                }
            }
        }

    }

    private fun setupRv() {
        binding.recyclerView.apply {
            characterAdapter = CharacterAdapter(this@CharacterFragment)
            adapter = characterAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            clipToPadding = false

        }
    }

    override fun onCharacterClick(character: Character) {
        val action =
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                character
            )

        findNavController().navigate(action)
    }
}