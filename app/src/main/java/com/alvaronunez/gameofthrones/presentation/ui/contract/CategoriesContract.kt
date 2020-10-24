package com.alvaronunez.gameofthrones.presentation.ui.contract

import com.alvaronunez.gameofthrones.data.models.CategoryDTO

class CategoriesContract {

    interface View {
        fun navigateToBooks()
        fun navigateToHouses()
        fun navigateToChars()
        fun loadCategories(categories: List<CategoryDTO>) // TODO: 24/10/2020 get categories from splash activity instead?? Should I get them from the repo or call repo in splash and categories has no sense?
    }

    interface Presenter {
        fun onCategoryClicked(category: CategoryDTO)
    }
}