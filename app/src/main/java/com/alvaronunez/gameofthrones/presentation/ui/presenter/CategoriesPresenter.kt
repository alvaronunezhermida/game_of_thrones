package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import com.alvaronunez.gameofthrones.presentation.ui.contract.CategoriesContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class CategoriesPresenter(
        private val view: CategoriesContract.View,
        private val getCategories: GetCategories,
        uiDispatcher: CoroutineDispatcher): CategoriesContract.Presenter, Scope by Scope.Impl(uiDispatcher) {

    override fun onCreate() {
        initScope()

        launch {
            getCategories.invoke { result ->
                when(result) {
                    is Result.Response -> {
                        view.loadCategories(result.data.sortedBy { it.name })
                    }
                    is Result.Error -> {
                        view.showError()
                    }
                }
            }
        }

    }

    override fun onCategoryClicked(category: CategoryDTO) {
        when (category.type) {
            0 -> view.navigateToBooks()
            1 -> view.navigateToHouses()
            2 -> view.navigateToChars()
        }
    }


    override fun onDestroy() {
        destroyScope()
    }




}