package com.example.fail.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fail.network.api.ApiService
import com.example.fail.network.model.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import com.example.fail.network.model.Character
import retrofit2.Response
import javax.inject.Inject

class FirstRepository @Inject constructor(
    private val api: ApiService
) {
    fun fetchCharacters(): LiveData<List<Character>> {
        val data = MutableLiveData<List<Character>>()
        api.fetchCharacter().enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        data.postValue(it.characters)
                    }
                }
            }
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                data.postValue(emptyList())
            }
        })
        return data
    }
}