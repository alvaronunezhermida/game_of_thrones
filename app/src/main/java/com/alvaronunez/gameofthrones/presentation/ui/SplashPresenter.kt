package com.alvaronunez.gameofthrones.presentation.ui

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import kotlinx.coroutines.launch

class SplashPresenter(private val getCategories: GetCategories): Scope by Scope.Impl() {

    interface View {
        fun navigateToCategories()
    }

    fun onCreate() {
        initScope()

        launch {
            getCategories.invoke { result ->
                when(result) {
                    is Result.Response -> {
                        // TODO: 20/10/2020 Load categories screen
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