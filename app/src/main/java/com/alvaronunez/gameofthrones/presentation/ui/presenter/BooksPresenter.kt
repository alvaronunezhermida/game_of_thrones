package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.domain.usecases.GetBooks
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import com.alvaronunez.gameofthrones.presentation.ui.contract.BooksContract
import kotlinx.coroutines.launch

class BooksPresenter(
        private val view: BooksContract.View,
        private val getBooks: GetBooks): BooksContract.Presenter, Scope by Scope.Impl() {

    override fun onCreate() {
        initScope()

        launch {
            view.showProgress()
            getBooks.invoke { result ->
                when(result) {
                    is Result.Response -> {
                        view.loadBooks(result.data)
                    }
                    is Result.Error -> {
                        // TODO: 20/10/2020 Handle error
                    }
                }
            }
            view.hideProgress()
        }

    }


    override fun onDestroy() {
        destroyScope()
    }




}