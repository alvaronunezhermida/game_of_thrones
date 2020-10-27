package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.domain.usecases.GetBooks
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import com.alvaronunez.gameofthrones.presentation.ui.contract.BooksContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

//TODO 25/10/2020 Set dispatcher as a constructor var in the presenter
class BooksPresenter(
        private val view: BooksContract.View,
        private val getBooks: GetBooks,
        uiDispatcher: CoroutineDispatcher): BooksContract.Presenter, Scope by Scope.Impl(uiDispatcher) {

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
                        view.showError()
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