package com.alvaronunez.gameofthrones.usecases

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.repository.Repository
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCategoriesTest {

    @RelaxedMockK
    private lateinit var mockRepository: Repository

    lateinit var getCategories: GetCategories

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getCategories = GetCategories(mockRepository)
    }

    @Test
    fun `invoke calls getCategories from repository`() {
        coEvery { mockRepository.getCategories() } returns Result.Response(listOf())
        runBlocking {
            getCategories.invoke{}
            coVerify { mockRepository.getCategories() }
        }

    }

}