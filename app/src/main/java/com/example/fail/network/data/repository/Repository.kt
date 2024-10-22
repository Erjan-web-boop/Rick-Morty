package com.example.fail.network.data.repository

import android.util.Log
import com.example.fail.network.data.api.ApiService
import com.example.fail.network.data.base.BaseRepository
import com.example.fail.network.data.model.BaseResponse
import com.example.fail.network.data.episodes.EpisodeResponse

class Repository(
    private val api: ApiService
) : BaseRepository() {

    fun fetchCharacter(id: Int) = doRequest { api.fetchCharacter(id) }

    fun getEpisodes(id: Int) = doRequest { api.getEpisodes(id) }
}
