package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import kotlinx.coroutines.launch

class CategoriesPresenter(private val getCategories: GetCategories): Scope by Scope.Impl() {

    interface View {
        fun navigateToBooks()
        fun navigateToHouses()
        fun navigateToChars()
        fun loadCategories(categories: List<CategoryDTO>) // TODO: 24/10/2020 get categories from splash activity instead?? Should I get them from the repo or call repo in splash and categories has no sense?
    }

    private var view: View? = null

    fun onCreate(view: View) {
        initScope()
        this.view = view

        launch {
            getCategories.invoke { result ->
                when(result) {
                    is Result.Response -> {
                        view.loadCategories(result.data)
                    }
                    is Result.Error -> {
                        // TODO: 20/10/2020 Handle error
                    }
                }
            }
        }

    }

    fun onCategoryClicked(category: CategoryDTO) {
        when (category.type) {
            0 -> view?.navigateToBooks()
            1 -> view?.navigateToHouses()
            2 -> view?.navigateToChars()
        }
    }


    fun onDestroy() {
        this.view = null
        destroyScope()
    }




}