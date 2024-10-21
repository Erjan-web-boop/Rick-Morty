package com.example.fail.network.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fail.network.data.episodes.EpisodeResponse
import com.example.fail.network.resource.Resource
import com.example.fail.network.data.model.Character
import com.example.fail.network.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor (
    private val repository: Repository
) : ViewModel() {

    val characters: LiveData<Resource<List<Character>>> = repository.fetchCharacters()
    val episodes: LiveData<Resource<EpisodeResponse>> = repository.getEpisodes()
}