package com.example.fail.network.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fail.network.data.episodes.Episode
import com.example.fail.R

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private var episodes: List<Episode> = listOf()

    fun submitList(list: List<Episode>) {
        episodes = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.bind(episode)
    }

    override fun getItemCount(): Int = episodes.size

    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val episodeName: TextView = itemView.findViewById(R.id.tvEpisodeName)
        private val episodeAirDate: TextView = itemView.findViewById(R.id.tvAirDate)

        fun bind(episode: Episode) {
            episodeName.text = episode.name
            episodeAirDate.text = episode.airDate
        }
    }
}
