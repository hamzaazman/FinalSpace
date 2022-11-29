package com.hamzaazman.finalspace.ui.location

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamzaazman.finalspace.R
import com.hamzaazman.finalspace.databinding.FragmentLocationBinding
import com.hamzaazman.finalspace.model.Location
import com.hamzaazman.finalspace.ui.location.adapter.LocationAdapter
import com.hamzaazman.finalspace.ui.location.viewmodel.LocationViewModel
import com.hamzaazman.finalspace.util.Resource
import com.hamzaazman.finalspace.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : Fragment(R.layout.fragment_location),
    LocationAdapter.OnLocationClickListener {
    private val binding by viewBinding(FragmentLocationBinding::bind)

    private val locationViewModel: LocationViewModel by viewModels()
    private lateinit var locationAdapter: LocationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observeLocationsData()
    }

    private fun observeLocationsData() {
        locationViewModel.locations.observe(viewLifecycleOwner, Observer { resource ->
            with(binding) {
                when (resource) {
                    is Resource.Error -> {
                        locationRecyclerView.isGone = true
                        Toast.makeText(
                            requireContext(), resource.message.toString(), Toast.LENGTH_LONG
                        ).show()
                    }
                    is Resource.Loading -> {
                        locationRecyclerView.isGone = true
                        progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        progressBar.isGone = true
                        locationRecyclerView.isVisible = true
                        resource.data.let { locationAdapter.differ.submitList(it) }
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        locationAdapter = LocationAdapter(this@LocationFragment)
        binding.locationRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationAdapter
            setHasFixedSize(true)
        }
    }

    override fun onLocationClick(location: Location) {
        val action =
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(location)
        findNavController().navigate(action)
    }
}