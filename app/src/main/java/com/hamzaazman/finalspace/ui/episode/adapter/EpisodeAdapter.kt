package com.hamzaazman.finalspace.ui.episode.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hamzaazman.finalspace.databinding.EpisodeRowItemBinding
import com.hamzaazman.finalspace.model.Episode

class EpisodeAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(EpisodeDiffUtil) {

    class EpisodeViewHolder(private val binding: EpisodeRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: Episode) {
            with(binding) {
                epNameTw.text = episode.name

                epImageView.load(episode.img_url) {
                    crossfade(true)
                }
            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeViewHolder {
        return EpisodeViewHolder(
            EpisodeRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val currentEpisodeList = currentList[position]
        holder.bind(currentEpisodeList)
        holder.itemView.setOnClickListener {
            listener.onEpisodeClick(currentEpisodeList)
        }
    }

    object EpisodeDiffUtil : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onEpisodeClick(episode: Episode)
    }
}
