package com.example.fail.network.data.api


import com.example.fail.network.data.episodes.EpisodeResponse
import com.example.fail.network.data.model.BaseResponse
import retrofit2.Call
import com.example.fail.network.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    suspend fun fetchCharacter(): Response<BaseResponse>

    @GET("character/{id}")
    suspend fun fetchCharacterById(@Path("id") id: Int): Response<Character>

    @GET("episode")
    suspend fun getEpisodes(): Response<EpisodeResponse>


}