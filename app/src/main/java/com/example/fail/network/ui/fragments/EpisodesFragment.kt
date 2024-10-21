package com.example.fail.network.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fail.databinding.FragmentEpisodesBinding
import com.example.fail.network.resource.Resource
import com.example.fail.network.viewmodel.CharacterViewModel
import com.example.fail.network.ui.adapter.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding
    private lateinit var viewModel: CharacterViewModel
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        episodeAdapter = EpisodeAdapter()
        binding.rvEpisodes.layoutManager = LinearLayoutManager(context)
        binding.rvEpisodes.adapter = episodeAdapter

        viewModel.episodes.observe(viewLifecycleOwner) { resource ->
            binding.progressBar.visibility = if (resource is Resource.Loading) View.VISIBLE else View.GONE
            when (resource) {
                is Resource.Success -> {
                    resource.data?.results?.let { episodeAdapter.submitList(it) }
                    binding.rvEpisodes.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                }
                else -> Unit
            }
        }
    }
}
