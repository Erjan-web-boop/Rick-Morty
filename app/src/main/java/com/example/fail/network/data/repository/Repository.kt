package com.example.fail.network.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.fail.network.resource.Resource
import com.example.fail.network.data.api.ApiService
import com.example.fail.network.data.episodes.EpisodeResponse
import com.example.fail.network.data.model.Character
import kotlinx.coroutines.Dispatchers
import okio.IOException

class Repository (
    private val api: ApiService
) {

    fun fetchCharacters(): LiveData<Resource<List<Character>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = api.fetchCharacter()
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!.characters))
            } else {
                emit(Resource.Error("Error: ${response.code()}"))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Error: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }

    fun getEpisodes(): LiveData<Resource<EpisodeResponse>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = api.getEpisodes()
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error("Error: ${response.code()}"))
            }
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

}
