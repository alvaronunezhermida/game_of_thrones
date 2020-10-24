package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import com.alvaronunez.gameofthrones.presentation.ui.contract.SplashContract
import kotlinx.coroutines.launch

class SplashPresenter(
        private val view: SplashContract.View,
        private val getCategories: GetCategories): SplashContract.Presenter, Scope by Scope.Impl() {


    fun onCreate() {
        initScope()

        launch {
            getCategories.invoke { result ->
                when(result) {
                    is Result.Response -> {
                        view.navigateToCategories()
                    }
                    is Result.Error -> {
                        // TODO: 20/10/2020 Handle error
                    }
                }
            }
        }
    }


    fun onDestory() {
        destroyScope()
    }




}