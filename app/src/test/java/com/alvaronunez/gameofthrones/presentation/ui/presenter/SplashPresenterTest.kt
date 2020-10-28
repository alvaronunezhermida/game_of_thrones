package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.domain.usecases.GetBooks
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.ui.contract.BooksContract
import com.alvaronunez.gameofthrones.presentation.ui.contract.SplashContract
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test

class SplashPresenterTest {

    @RelaxedMockK
    lateinit var view: SplashContract.View

    @RelaxedMockK
    lateinit var getCategories: GetCategories

    lateinit var splashPresenter: SplashPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        splashPresenter = SplashPresenter(view, getCategories, Dispatchers.Unconfined)
    }

    @Test
    fun `test view_navigateToCategories() are called when data is retrieved`() {

        val captureCallback = slot<(Result<List<CategoryDTO>>)-> Unit>()
        coEvery { getCategories.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<CategoryDTO>())
            captureCallback.captured.invoke(fakeResult)
        }

        splashPresenter.onCreate()
        coVerify { view.navigateToCategories() }
    }

    @Test
    fun `test view_showError() are called when data is retrieved with error`() {
        val captureCallback = slot<(Result<List<CategoryDTO>>)-> Unit>()
        coEvery { getCategories.invoke(capture(captureCallback)) } answers {
            val fakeError = Result.Error()
            captureCallback.captured.invoke(fakeError)
        }
        splashPresenter.onCreate()
        coVerify { view.showError() }
    }

}