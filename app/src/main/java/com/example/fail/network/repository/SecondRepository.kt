package com.example.fail.network.repository

import androidx.lifecycle.MutableLiveData
import com.example.fail.network.resource.Resource
import com.example.fail.network.api.ApiService
import retrofit2.Call
import com.example.fail.network.model.Character
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SecondRepository @Inject constructor(private val api: ApiService) {

    fun getCharacterById(id: Int): MutableLiveData<Resource<Character?>> {
        val data = MutableLiveData<Resource<Character?>>()
        data.postValue(Resource.Loading())

        api.fetchCharacterById(id).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful) {
                    data.postValue(Resource.Success(response.body()))
                } else {
                    data.postValue(Resource.Error("Error${response.code()}"))
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                data.postValue(Resource.Error("Error:${t.message}"))
            }
        })
        return data
    }
}

