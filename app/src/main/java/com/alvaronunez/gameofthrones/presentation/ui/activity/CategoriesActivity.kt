package com.alvaronunez.gameofthrones.presentation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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

    private val adapter: CategoriesAdapter = CategoriesAdapter(presenter::onCategoryClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        presenter.onCreate()

        findViewById<RecyclerView>(R.id.categoriesRecycler).adapter = adapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun navigateToBooks() {
        startActivity(Intent(this, BooksActivity::class.java))
    }

    override fun navigateToHouses() {
        startActivity(Intent(this, HousesActivity::class.java))
    }

    override fun navigateToChars() {
        startActivity(Intent(this, CharsActivity::class.java))
    }

    override fun showError() {
        AlertDialog.Builder(this)
                .setTitle(R.string.error_title)
                .setMessage(R.string.error_description)
                .setPositiveButton(R.string.ok) { _, _ ->
                    finish()
                }
                .show()
    }

    override fun loadCategories(categories: List<CategoryDTO>) {
        adapter.categories = categories
    }


}