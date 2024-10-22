package com.example.fail.network.data.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.fail.network.resource.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> Response<T>): LiveData<Resource<T>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = request()
                if (response.isSuccessful && response.body() != null) {
                    emit(Resource.Success(response.body()!!))
                } else {
                    emit(Resource.Error("Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: IOException) {
                emit(Resource.Error("Error: ${e.localizedMessage ?: "Unknown error"}"))
            }
        }
    }
}
