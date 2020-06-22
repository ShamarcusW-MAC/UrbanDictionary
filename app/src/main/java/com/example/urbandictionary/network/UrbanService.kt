package com.example.urbandictionary.network

import com.example.urbandictionary.model.DefinitionResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UrbanService {

    @GET("/define")
    @Headers("x-rapidapi-host:mashape-community-urban-dictionary.p.rapidapi.com", "x-rapidapi-key:d529666efcmsh1f5e3d2dc08f4b6p16ebf8jsndb1d68f87792")
    fun getDefinition(@Query("term") input : String) : Observable<DefinitionResponse>

}
