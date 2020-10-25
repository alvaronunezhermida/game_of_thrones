package com.alvaronunez.gameofthrones.presentation.ui.contract

import com.alvaronunez.gameofthrones.data.models.BookDTO

class BooksContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError()
        fun loadBooks(books: List<BookDTO>)
    }

    interface Presenter {
        fun onCreate()
        fun onDestroy()
    }
}