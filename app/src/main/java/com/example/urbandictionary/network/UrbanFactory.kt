package com.example.urbandictionary.network

import android.util.Log
import com.example.urbandictionary.model.Definition
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UrbanFactory {

    private val BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com"
    private val urbanService : UrbanService

    init {
        urbanService = createService(retrofitInstance())
    }

    private fun retrofitInstance(): Retrofit{
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d("TAG_X", message)
        })
        loggingInterceptor.level = (HttpLoggingInterceptor.Level.BODY)
        val httpClient =  OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //convert JSON objects to POJO
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//mapping responses RxJava
            .build()
    }

    private fun createService(retrofit: Retrofit) : UrbanService {
        return retrofit.create(UrbanService::class.java)
    }

    fun getDefinitions(searchWord: String): Observable<List<Definition>> {
        return urbanService.getDefinition(searchWord).map {
            it.definitionList
        }
    }
}