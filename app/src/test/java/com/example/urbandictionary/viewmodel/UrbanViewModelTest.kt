package com.example.urbandictionary.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.urbandictionary.model.Definition
import com.example.urbandictionary.network.UrbanFactory
import com.example.urbandictionary.util.RxJavaScheduler
import com.example.urbandictionary.util.testObserver
import io.reactivex.Observable
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


@RunWith(BlockJUnit4ClassRunner::class)
class UrbanViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rxScheduler = RxJavaScheduler()

    @Mock
    lateinit var factory : UrbanFactory

    lateinit var vm: UrbanViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        vm = UrbanViewModel()
    }

    @Test
    fun testViewModel(){
        val state = vm.definitionData.testObserver()

        val definitionList: List<Definition> = mockDefinitions()

        Mockito.`when`(factory.getDefinitions("game")).thenReturn(Observable.just(definitionList))

        vm.makeCall("game")

        assertEquals(definitionList, state.observedValues)

    }

    private fun mockDefinitions(): List<Definition> {
        val definition = Definition()
        val definition1 = Definition()

        definition.word = "game"
        definition.definition = "Something that is fun and entertaining"
        definition.writtenOn = "2020-06-20T00:00:00.000Z"
        definition.soundUrls = emptyList()
        definition.permalink = "http://game.urbanup.com/700500"
        definition.example = "[Game] is the definition of entertainment."
        definition.defid = 700500
        definition.currentVote = ""
        definition.author = "Shamarcus Walker"
        definition.thumbsDown = 50
        definition.thumbsUp = 100

        definition1.word = "game"
        definition1.definition = "Usually a competitive actvity"
        definition1.writtenOn = "2020-06-19T00:00:00.000Z"
        definition1.soundUrls = emptyList()
        definition1.permalink = "http://game.urbanup.com/700501"
        definition1.example = "[Game] is a way to get really competitive."
        definition1.defid = 700501
        definition1.currentVote = ""
        definition1.author = "Shamarcus Walker"
        definition1.thumbsDown = 100
        definition1.thumbsUp = 150
        return listOf(definition, definition1)
    }

}