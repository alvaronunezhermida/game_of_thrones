package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.data.models.HouseDTO
import com.alvaronunez.gameofthrones.domain.usecases.GetHouses
import com.alvaronunez.gameofthrones.presentation.ui.contract.HousesContract
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test

class HousesPresenterTest {

    @RelaxedMockK
    lateinit var view: HousesContract.View

    @RelaxedMockK
    lateinit var getHouses: GetHouses

    lateinit var housesPresenter: HousesPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        housesPresenter = HousesPresenter(view, getHouses, Dispatchers.Unconfined)
    }

    @Test
    fun `test view_loadHouses() are called when data is retrieved`() {

        val captureCallback = slot<(Result<List<HouseDTO>>)-> Unit>()
        coEvery { getHouses.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<HouseDTO>())
            captureCallback.captured.invoke(fakeResult)
        }

        housesPresenter.onCreate()
        coVerify { view.loadHouses(any()) }
    }

    @Test
    fun `test view_showError() are called when data is retrieved with error`() {
        val captureCallback = slot<(Result<List<HouseDTO>>)-> Unit>()
        coEvery { getHouses.invoke(capture(captureCallback)) } answers {
            val fakeError = Result.Error()
            captureCallback.captured.invoke(fakeError)
        }
        housesPresenter.onCreate()
        coVerify { view.showError() }
    }

    @Test
    fun `test view_showProgress() are called when data is retrieved`() {

        housesPresenter.onCreate()
        coVerify { view.showProgress() }
    }

    @Test
    fun `test view_hideProgress() are called when data is retrieved`() {

        housesPresenter.onCreate()
        coVerify { view.hideProgress() }
    }

}