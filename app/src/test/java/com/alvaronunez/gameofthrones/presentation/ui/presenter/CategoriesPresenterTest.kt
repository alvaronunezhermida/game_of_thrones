package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.data.models.CategoryMO
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.ui.contract.CategoriesContract
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test

class CategoriesPresenterTest {

    @RelaxedMockK
    lateinit var view: CategoriesContract.View

    @RelaxedMockK
    lateinit var getCategories: GetCategories

    lateinit var categoriesPresenter: CategoriesPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        categoriesPresenter = CategoriesPresenter(view, getCategories, Dispatchers.Unconfined)
    }

    @Test
    fun `test view_loadCategories() are called when data is retrieved`() {

        val captureCallback = slot<(Result<List<CategoryDTO>>)-> Unit>()
        coEvery { getCategories.invoke(capture(captureCallback)) } answers {
            val fakeResult = Result.Response(listOf<CategoryDTO>())
            captureCallback.captured.invoke(fakeResult)
        }

        categoriesPresenter.onCreate()
        coVerify { view.loadCategories(any()) }
    }

    @Test
    fun `test view_showError() are called when data is retrieved with error`() {
        val captureCallback = slot<(Result<List<CategoryDTO>>)-> Unit>()
        coEvery { getCategories.invoke(capture(captureCallback)) } answers {
            val fakeError = Result.Error()
            captureCallback.captured.invoke(fakeError)
        }
        categoriesPresenter.onCreate()
        coVerify { view.showError() }
    }

    @Test
    fun `test view_navigateToBooks() are called when onCategoryClicked receive a category type 0`() {
        val category = CategoryMO.categoryType0()

        categoriesPresenter.onCategoryClicked(category)
        verify { view.navigateToBooks() }
    }

    @Test
    fun `test view_navigateToHouses() are called when onCategoryClicked receive a category type 1`() {
        val category = CategoryMO.categoryType1()

        categoriesPresenter.onCategoryClicked(category)
        verify { view.navigateToHouses() }
    }

    @Test
    fun `test view_navigateToChars() are called when onCategoryClicked receive a category type 2`() {
        val category = CategoryMO.categoryType2()

        categoriesPresenter.onCategoryClicked(category)
        verify { view.navigateToChars() }
    }

}