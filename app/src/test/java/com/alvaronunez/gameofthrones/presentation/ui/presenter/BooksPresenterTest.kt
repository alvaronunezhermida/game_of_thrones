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
import org.junit.Before
import org.junit.Test

class BooksPresenterTest {

    @RelaxedMockK
    lateinit var view: BooksContract.View

    @RelaxedMockK
    lateinit var getBooks: GetBooks

    lateinit var booksPresenter: BooksPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        booksPresenter = BooksPresenter(view, getBooks, Dispatchers.Unconfined)
    }

    @Test
    fun `test view_loadBooks() are called when data is retrieved`() {

        val captureCallback = slot<(Result<List<BookDTO>>)-> Unit>()
        coEvery { getBooks.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<BookDTO>())
            captureCallback.captured.invoke(fakeResult)
        }

        booksPresenter.onCreate()
        coVerify { view.loadBooks(any()) }
    }

    @Test
    fun `test view_showError() are called when data is retrieved with error`() {
        val captureCallback = slot<(Result<List<BookDTO>>)-> Unit>()
        coEvery { getBooks.invoke(capture(captureCallback)) } answers {
            val fakeError = Result.Error()
            captureCallback.captured.invoke(fakeError)
        }
        booksPresenter.onCreate()
        coVerify { view.showError() }
    }

    @Test
    fun `test view_showProgress() are called when data is retrieved`() {

        booksPresenter.onCreate()
        coVerify { view.showProgress() }
    }

    @Test
    fun `test view_hideProgress() are called when data is retrieved`() {

        booksPresenter.onCreate()
        coVerify { view.hideProgress() }
    }

}