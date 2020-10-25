package com.alvaronunez.gameofthrones.presentation.ui.presenter

import android.text.GetChars
import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.domain.usecases.GetCharacters
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import com.alvaronunez.gameofthrones.presentation.ui.contract.CharsContract
import kotlinx.coroutines.launch

class CharsPresenter(
        private val view: CharsContract.View,
        private val getChars: GetCharacters): CharsContract.Presenter, Scope by Scope.Impl() {

    override fun onCreate() {
        initScope()

        launch {
            view.showProgress()
            getChars.invoke { result ->
                when(result) {
                    is Result.Response -> {
                        view.loadChars(result.data)
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