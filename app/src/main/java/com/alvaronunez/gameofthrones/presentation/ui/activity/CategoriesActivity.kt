package com.alvaronunez.gameofthrones.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.presentation.ui.adapter.CategoriesAdapter
import com.alvaronunez.gameofthrones.presentation.ui.contract.CategoriesContract
import com.alvaronunez.gameofthrones.presentation.ui.presenter.CategoriesPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CategoriesActivity : AppCompatActivity(), CategoriesContract.View {

    private val presenter: CategoriesPresenter by inject { parametersOf(this) }

    private lateinit var adapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        presenter.onCreate()

        adapter = CategoriesAdapter(presenter::onCategoryClicked)
        findViewById<RecyclerView>(R.id.categoriesRecycler).adapter = adapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun navigateToBooks() {
        TODO("Not yet implemented")
    }

    override fun navigateToHouses() {
        TODO("Not yet implemented")
    }

    override fun navigateToChars() {
        TODO("Not yet implemented")
    }

    override fun loadCategories(categories: List<CategoryDTO>) {
        adapter.categories = categories
    }


}