package com.alvaronunez.gameofthrones.usecases

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.repository.Repository
import com.alvaronunez.gameofthrones.domain.usecases.GetHouses
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetHousesTest {

    @RelaxedMockK
    private lateinit var mockRepository: Repository

    lateinit var getHouses: GetHouses

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getHouses = GetHouses(mockRepository)
    }

    @Test
    fun `invoke calls getHouses from repository`() {
        coEvery { mockRepository.getHouses() } returns Result.Response(listOf())
        runBlocking {
            getHouses.invoke{}
            coVerify { mockRepository.getHouses() }
        }

    }

}