package com.hamzaazman.finalspace.ui.location.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hamzaazman.finalspace.databinding.ResidentRowItemBinding
import com.hamzaazman.finalspace.model.Character


class ResidentAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ResidentAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ResidentRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            with(binding) {
                residentCharacterName.text = character.name
                residentCharacterImage.load(character.img_url) {
                    crossfade(true)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ResidentRowItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = differ.currentList[position]
        holder.bind(currentList)
        holder.itemView.setOnClickListener {
            listener.onCharacterClick(currentList)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val residentDiffUtilCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, residentDiffUtilCallback)

    interface OnItemClickListener {
        fun onCharacterClick(character: Character)
    }

}