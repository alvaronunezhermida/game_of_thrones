package com.alvaronunez.gameofthrones.presentation.ui.contract

import com.alvaronunez.gameofthrones.data.models.CategoryDTO

class CategoriesContract {

    interface View {
        fun navigateToBooks()
        fun navigateToHouses()
        fun navigateToChars()
        fun loadCategories(categories: List<CategoryDTO>)
    }

    interface Presenter {
        fun onCreate()
        fun onDestroy()
        fun onCategoryClicked(category: CategoryDTO)
    }
}