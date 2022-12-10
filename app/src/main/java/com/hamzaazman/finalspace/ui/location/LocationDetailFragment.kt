package com.hamzaazman.finalspace.ui.location

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.hamzaazman.finalspace.R
import com.hamzaazman.finalspace.databinding.FragmentLocationDetailBinding
import com.hamzaazman.finalspace.model.Character
import com.hamzaazman.finalspace.ui.character.viewmodel.CharacterViewModel
import com.hamzaazman.finalspace.ui.location.adapter.ResidentAdapter
import com.hamzaazman.finalspace.util.Resource
import com.hamzaazman.finalspace.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

//val residentCharacterRange = it.substring(it.lastIndexOf("/") + 1).toInt()

@AndroidEntryPoint
class LocationDetailFragment : Fragment(R.layout.fragment_location_detail),
    ResidentAdapter.OnItemClickListener {
    private val binding by viewBinding(FragmentLocationDetailBinding::bind)

    private val args: LocationDetailFragmentArgs by navArgs()
    private lateinit var residentAdapter: ResidentAdapter
    private val characterViewModel: CharacterViewModel by viewModels()
    private var tempList: ArrayList<Character> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val location = args.location

        with(binding) {
            locationDetailName.text = location.name
            locationDetailType.text = location.type

            if (location.notable_residents.isNullOrEmpty()) {
                infoCharacter.isGone = true
                characterCardView.isGone = true
            } else {
                infoCharacter.isVisible = true
                characterCardView.isVisible = true
            }

            residentChacterRv.apply {
                residentAdapter = ResidentAdapter(this@LocationDetailFragment)
                adapter = residentAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                val decorator =
                    DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
                addItemDecoration(decorator)
                clipToPadding = false
                setHasFixedSize(true)
            }

            locationDetailImage.load(location.img_url) {
                crossfade(true)
            }

            //Filter character for notable resident
            characterViewModel.characters.observe(
                viewLifecycleOwner
            ) { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            resource.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()

                    }
                    is Resource.Loading -> {
                        characterCardView.isGone = true
                      //  linearProgressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        characterCardView.isVisible = true
                        //linearProgressBar.isGone = true
                        resource.data.let { listCharacter ->

                            location.notable_residents?.forEach { url ->
                                val myUrl = Uri.parse(url)

                                val filteredCharacter = listCharacter?.filter { character ->
                                    character.id.toString() == myUrl.pathSegments[3]
                                }

                                filteredCharacter?.forEach {
                                    tempList.add(it)
                                }

                                residentChacterRv.isVisible = tempList.isNotEmpty()
                                residentAdapter.differ.submitList(tempList)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCharacterClick(character: Character) {
        val action =
            LocationDetailFragmentDirections.actionLocationDetailFragmentToCharacterBottomSheetFragment(
                character
            )
        findNavController().navigate(action)
    }
}