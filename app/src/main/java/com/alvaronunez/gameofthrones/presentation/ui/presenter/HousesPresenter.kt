package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.domain.usecases.GetHouses
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import com.alvaronunez.gameofthrones.presentation.ui.contract.HousesContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class HousesPresenter(
        private val view: HousesContract.View,
        private val getHouses: GetHouses,
        uiDispatcher: CoroutineDispatcher): HousesContract.Presenter, Scope by Scope.Impl(uiDispatcher) {

    override fun onCreate() {
        initScope()

        launch {
            view.showProgress()
            getHouses.invoke { result ->
                when(result) {
                    is Result.Response -> {
                        view.loadHouses(result.data)
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