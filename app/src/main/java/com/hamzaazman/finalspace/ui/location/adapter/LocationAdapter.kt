package com.hamzaazman.finalspace.ui.location.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hamzaazman.finalspace.databinding.LocationRowItemBinding
import com.hamzaazman.finalspace.model.Location

class LocationAdapter(private val listener: OnLocationClickListener) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(private val binding: LocationRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            with(binding) {
                locationName.text = location.name
                locationType.text = location.type
                locationImage.load(location.img_url) {
                    crossfade(true)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val currentLocation = differ.currentList[position]
        holder.bind(currentLocation)
        holder.itemView.setOnClickListener {
            listener.onLocationClick(currentLocation)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val locationDiffUtilCallback = object : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, locationDiffUtilCallback)

    interface OnLocationClickListener {
        fun onLocationClick(location: Location)
    }

}