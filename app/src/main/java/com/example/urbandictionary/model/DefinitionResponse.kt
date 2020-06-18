package com.example.urbandictionary.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DefinitionResponse {

    @SerializedName("list")
    @Expose
    var definitionList: List<Definition> = mutableListOf()
}