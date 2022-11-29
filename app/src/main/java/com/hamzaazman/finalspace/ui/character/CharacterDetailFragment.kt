package com.hamzaazman.finalspace.ui.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.hamzaazman.finalspace.R
import com.hamzaazman.finalspace.databinding.FragmentCharacterDetailBinding
import com.hamzaazman.finalspace.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character = args.character

        with(binding) {

            characterDetailName.text = character.name
            characterDetailStatus.text = character.status
            characterDetailGender.text = character.gender
            characterDetailOrigin.text = character.origin
            characterDetailSpecies.text = character.species

            characterDetailImage.load(character.img_url) {
                crossfade(true)
            }

        }
    }

}