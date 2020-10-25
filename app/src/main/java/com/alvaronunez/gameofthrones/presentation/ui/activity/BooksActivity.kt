package com.alvaronunez.gameofthrones.presentation.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.presentation.ui.adapter.BooksAdapter
import com.alvaronunez.gameofthrones.presentation.ui.contract.BooksContract
import com.alvaronunez.gameofthrones.presentation.ui.presenter.BooksPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class BooksActivity : AppCompatActivity(), BooksContract.View {

    private val presenter: BooksPresenter by inject { parametersOf(this) }

    private lateinit var adapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setTitle(R.string.books)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        presenter.onCreate()

        adapter = BooksAdapter()
        findViewById<RecyclerView>(R.id.listRecycler).adapter = adapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        findViewById<ProgressBar>(R.id.progress).visibility = View.VISIBLE
    }

    override fun hideProgress() {
        findViewById<ProgressBar>(R.id.progress).visibility = View.GONE
    }

    override fun loadBooks(books: List<BookDTO>) {
        adapter.books = books
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}