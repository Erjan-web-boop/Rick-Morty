package com.example.fail.network.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fail.network.data.episodes.EpisodeResponse
import com.example.fail.network.data.model.BaseResponse
import com.example.fail.network.resource.Resource
import com.example.fail.network.data.model.Character
import com.example.fail.network.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class CharacterViewModel(
    private val repository: Repository
) : ViewModel() {

    fun fetchCharacters(id: Int): LiveData<Resource<BaseResponse>> = repository.fetchCharacter(id)
    fun getEpisodes(id: Int): LiveData<Resource<EpisodeResponse>> = repository.getEpisodes(id)
}