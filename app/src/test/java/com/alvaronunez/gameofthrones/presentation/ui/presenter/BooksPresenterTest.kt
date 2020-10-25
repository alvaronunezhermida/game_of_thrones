package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.domain.usecases.GetBooks
import com.alvaronunez.gameofthrones.presentation.ui.contract.BooksContract
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BooksPresenterTest {

    @RelaxedMockK
    lateinit var view: BooksContract.View

    @RelaxedMockK
    lateinit var getBooks: GetBooks

    lateinit var booksPresenter: BooksPresenter

    //TODO 25/10/2020 Set dispatcher as a constructor var in the presenter
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this)
        booksPresenter = BooksPresenter(view, getBooks)
    }

    @Test
    fun `test view_loadBooks() are called when data is retrieved`() {
        val captureCallback = slot<(Result<List<BookDTO>>)-> Unit>()
        coEvery { getBooks.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<BookDTO>())
            captureCallback.captured.invoke(fakeResult)
        }
        /*coEvery { getBooks.invoke(any())  } answers {
            firstArg<(Result<List<BookDTO>>) -> Unit>().invoke(Result.Response(listOf()))
        }*/
        booksPresenter.onCreate()
        coVerify { view.loadBooks(any()) }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}