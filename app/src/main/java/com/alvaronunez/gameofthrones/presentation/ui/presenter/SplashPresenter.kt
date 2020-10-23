package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import kotlinx.coroutines.launch

class SplashPresenter(private val getCategories: GetCategories): Scope by Scope.Impl() {

    interface View {
        fun navigateToCategories()
    }

    private var view: View? = null

    fun onCreate(view: View) {
        initScope()
        this.view = view

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
        this.view = null
        destroyScope()
    }




}