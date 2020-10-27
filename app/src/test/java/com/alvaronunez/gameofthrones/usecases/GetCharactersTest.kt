package com.alvaronunez.gameofthrones.usecases

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.repository.Repository
import com.alvaronunez.gameofthrones.domain.usecases.GetCharacters
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharactersTest {

    @RelaxedMockK
    private lateinit var mockRepository: Repository

    lateinit var getCharacters: GetCharacters

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getCharacters = GetCharacters(mockRepository)
    }

    @Test
    fun `invoke calls getCharacters from repository`() {
        coEvery { mockRepository.getCharacters() } returns Result.Response(listOf())
        runBlocking {
            getCharacters.invoke{}
            coVerify { mockRepository.getCharacters() }
        }

    }

}