package com.example.fail.network.di


import com.example.fail.BuildConfig
import com.example.fail.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    @Provides
    fun provideApi(retrofit: Retrofit): ApiService {
        return  retrofit.create(ApiService::class.java)

    }

}