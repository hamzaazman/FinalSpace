package com.hamzaazman.finalspace.ui.location.bottomsheet

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hamzaazman.finalspace.R
import com.hamzaazman.finalspace.databinding.CharacterBottomSheetBinding
import com.hamzaazman.finalspace.util.viewBinding

class CharacterBottomSheetFragment : BottomSheetDialogFragment(R.layout.character_bottom_sheet) {
    private val args: CharacterBottomSheetFragmentArgs by navArgs()
    private val binding by viewBinding(CharacterBottomSheetBinding::bind)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character = args.character

        with(binding) {
            with(character) {
                characterDetailName.text = name
                characterDetailStatus.text = status
                characterDetailGender.text = gender

                if (character.species.isNullOrEmpty()) {
                    characterDetailSpecies.text = "Unknown"
                } else {
                    characterDetailSpecies.text = character.species
                }
            }

            characterDetailImage.load(character.img_url) {
                crossfade(true)
            }
        }
    }
}

