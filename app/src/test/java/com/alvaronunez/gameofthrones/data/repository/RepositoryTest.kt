package com.alvaronunez.gameofthrones.data.repository

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryMO
import com.alvaronunez.gameofthrones.data.source.LocalDataSource
import com.alvaronunez.gameofthrones.data.source.RemoteDataSource
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before

class RepositoryTest {

    private lateinit var sut: Repository

    @RelaxedMockK
    private lateinit var mockLocalDataSource: LocalDataSource

    @RelaxedMockK
    private lateinit var mockRemoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = Repository(mockLocalDataSource, mockRemoteDataSource)
    }

    @Test
    fun `getCategories execute isCategoriesEmpty from local`() {

        coEvery { sut.getCategories() } returns Result.Response(listOf())
        coEvery { mockLocalDataSource.isCategoriesEmpty() } returns false

        runBlocking {
            sut.getCategories()
            coVerify { mockLocalDataSource.isCategoriesEmpty() }
        }
    }

    @Test
    fun `getCategories execute getCategories from local when there is categories in local`() {

        coEvery { sut.getCategories() } returns Result.Response(listOf())
        coEvery { mockLocalDataSource.isCategoriesEmpty() } returns false

        runBlocking {
            sut.getCategories()
            coVerify { mockLocalDataSource.getCategories() }
        }
    }

    @Test
    fun `getCategories execute getCategories from remote when there isn't categories in local`() {
        coEvery { sut.getCategories() } returns Result.Response(listOf())
        coEvery { mockLocalDataSource.isCategoriesEmpty() } returns true
        coEvery { mockRemoteDataSource.getCategories() } returns Result.Response(listOf())

        runBlocking {
            sut.getCategories()
            coVerify { mockRemoteDataSource.getCategories() }
        }
    }

    @Test
    fun `getCategories execute saveCategories to local after getting categories from remote`() {
        val categories = listOf(CategoryMO.normalCategory(), CategoryMO.categoryType0())
        coEvery { mockLocalDataSource.isCategoriesEmpty() } returns true
        coEvery { mockRemoteDataSource.getCategories() } returns Result.Response(categories)

        runBlocking {
            sut.getCategories()
            coVerify { mockLocalDataSource.saveCategories(categories) }
        }
    }

    @Test
    fun `getBooks execute getBooks from remote`() {
        runBlocking {
            sut.getBooks()
            coVerify { mockRemoteDataSource.getBooks() }
        }
    }

    @Test
    fun `getHouses execute getHouses from remote`() {
        runBlocking {
            sut.getHouses()
            coVerify { mockRemoteDataSource.getHouses() }
        }
    }

    @Test
    fun `getCharacters execute getCharacters from remote`() {
        runBlocking {
            sut.getCharacters()
            coVerify { mockRemoteDataSource.getCharacters() }
        }
    }

}