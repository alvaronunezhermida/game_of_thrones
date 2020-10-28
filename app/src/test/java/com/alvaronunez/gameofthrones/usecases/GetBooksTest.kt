package com.alvaronunez.gameofthrones.usecases

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.data.repository.Repository
import com.alvaronunez.gameofthrones.domain.usecases.GetBooks
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetBooksTest {

    @RelaxedMockK
    private lateinit var mockRepository: Repository

    lateinit var getBooks: GetBooks

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getBooks = GetBooks(mockRepository)
    }

    @Test
    fun `invoke calls getBooks from repository`() {
        coEvery { mockRepository.getBooks() } returns Result.Response(listOf())
        runBlocking {
            getBooks.invoke{}
            coVerify { mockRepository.getBooks() }
        }

    }

}