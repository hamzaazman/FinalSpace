package com.hamzaazman.finalspace.ui.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.hamzaazman.finalspace.databinding.CharacterRowItemBinding
import com.hamzaazman.finalspace.model.Character

class CharacterAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(DiffCallback) {

    class CharacterViewHolder(private val binding: CharacterRowItemBinding) :
        ViewHolder(binding.root) {
        fun bind(character: Character) {
            with(binding) {

                chNameTextView.text = character.name


                /*chImageView.apply {
                    Glide.with(itemView.context)
                        .load(character.img_url)
                        .placeholder(circularProgressLoading(itemView.context))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .into(this)
                        .clearOnDetach()
                }*/

                chImageView.load(character.img_url) {
                    crossfade(true)
                }

            }

        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentList = currentList[position]
        holder.bind(currentList)
        holder.itemView.setOnClickListener {
            listener.onCharacterClick(currentList)
        }
    }

    interface OnItemClickListener {
        fun onCharacterClick(character: Character)
    }
}