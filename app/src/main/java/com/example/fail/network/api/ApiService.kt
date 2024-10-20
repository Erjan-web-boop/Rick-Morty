package com.example.fail.network.api


import com.example.fail.network.model.BaseResponse
import retrofit2.Call
import com.example.fail.network.model.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    fun fetchCharacter(): Call<BaseResponse>

    @GET("character/{id}")
    fun fetchCharacterById(@Path("id") id: Int): Call<Character>


}