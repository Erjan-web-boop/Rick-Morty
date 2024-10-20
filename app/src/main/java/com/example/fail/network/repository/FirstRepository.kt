package com.example.fail.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fail.network.resource.Resource
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
    fun fetchCharacters(): LiveData<Resource<List<Character>>> {
        val data = MutableLiveData<Resource<List<Character>>>()
        data.postValue(Resource.Loading())

        api.fetchCharacter().enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        data.postValue(Resource.Success(it.characters))
                    }?:run{
                        data.postValue(Resource.Error("Unknown"))
                    }
                }else{
                    data.postValue(Resource.Error("Error${response.code()}"))
                }
            }
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                data.postValue(Resource.Error("Error:${t.message}"))
            }
        })
        return data
    }
}