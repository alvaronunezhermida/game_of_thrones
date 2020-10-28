package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import com.alvaronunez.gameofthrones.presentation.ui.contract.SplashContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashPresenter(
        private val view: SplashContract.View,
        private val getCategories: GetCategories,
        uiDispatcher: CoroutineDispatcher): SplashContract.Presenter, Scope by Scope.Impl(uiDispatcher) {


    override fun onCreate() {
        initScope()

        launch {
            getCategories.invoke { result ->
                when(result) {
                    is Result.Response -> {
                        view.navigateToCategories()
                    }
                    is Result.Error -> {
                        view.showError()
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        destroyScope()
    }




}