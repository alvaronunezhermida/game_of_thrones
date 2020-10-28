package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CharDTO
import com.alvaronunez.gameofthrones.domain.usecases.GetCharacters
import com.alvaronunez.gameofthrones.presentation.ui.contract.CharsContract
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test

class CharsPresenterTest {

    @RelaxedMockK
    lateinit var view: CharsContract.View

    @RelaxedMockK
    lateinit var getCharacters: GetCharacters

    lateinit var charsPresenter: CharsPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        charsPresenter = CharsPresenter(view, getCharacters, Dispatchers.Unconfined)
    }

    @Test
    fun `test view_loadChars() are called when data is retrieved`() {

        val captureCallback = slot<(Result<List<CharDTO>>)-> Unit>()
        coEvery { getCharacters.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<CharDTO>())
            captureCallback.captured.invoke(fakeResult)
        }

        charsPresenter.onCreate()
        coVerify { view.loadChars(any()) }
    }

    @Test
    fun `test view_showError() are called when data is retrieved with error`() {
        val captureCallback = slot<(Result<List<CharDTO>>)-> Unit>()
        coEvery { getCharacters.invoke(capture(captureCallback)) } answers {
            val fakeError = Result.Error()
            captureCallback.captured.invoke(fakeError)
        }
        charsPresenter.onCreate()
        coVerify { view.showError() }
    }

    @Test
    fun `test view_showProgress() are called when data is retrieved`() {

        charsPresenter.onCreate()
        coVerify { view.showProgress() }
    }

    @Test
    fun `test view_hideProgress() are called when data is retrieved`() {

        charsPresenter.onCreate()
        coVerify { view.hideProgress() }
    }

}