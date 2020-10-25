package com.alvaronunez.gameofthrones.presentation.ui.presenter

import com.alvaronunez.gameofthrones.data.Result
import com.alvaronunez.gameofthrones.domain.usecases.GetHouses
import com.alvaronunez.gameofthrones.presentation.ui.common.Scope
import com.alvaronunez.gameofthrones.presentation.ui.contract.HousesContract
import kotlinx.coroutines.launch

class HousesPresenter(
        private val view: HousesContract.View,
        private val getHouses: GetHouses): HousesContract.Presenter, Scope by Scope.Impl() {

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