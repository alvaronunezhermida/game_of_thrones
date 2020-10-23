package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import kotlinx.coroutines.launch

class CategoriesPresenter: Scope by Scope.Impl() {

    interface View {
        fun navigateToBooks()
        fun navigateToHouses()
        fun navigateToChars()
    }

    private var view: View? = null

    fun onCreate(view: View) {
        initScope()
        this.view = view


    }

    fun onCategoryClicked(category: CategoryDTO) {
        when (category.type) {
            1 -> view?.navigateToBooks()
            2 -> view?.navigateToHouses()
            3 -> view?.navigateToChars()
        }
    }


    fun onDestroy() {
        this.view = null
        destroyScope()
    }




}