package com.example.urbandictionary.model

import org.junit.Assert.*
import org.junit.Test

class DefinitionTest {

    @Test
    fun dataTest() {
        var definition = Definition()
        definition.thumbsUp = 10
        definition.thumbsDown = 5
        definition.author = "Walker"
        definition.currentVote = ""
        definition.defid = 7256119
        definition.definition = "A dashing and handsome man known to game and entertain."
        definition.example = "Shamarcus has a passion when it comes to development."
        definition.permalink = "http://shamarcus.urbanup.com/7256119"
        definition.soundUrls = null
        definition.word = "Shamarcus"
        definition.writtenOn = "2020-03-15T00:00:00.000Z"


        assertEquals("Shamarcus", definition.word)
    }
}