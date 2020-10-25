package com.alvaronunez.gameofthrones.presentation.ui.contract

import com.alvaronunez.gameofthrones.data.models.HouseDTO

class HousesContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun loadHouses(houses: List<HouseDTO>)
    }

    interface Presenter {
        fun onCreate()
        fun onDestroy()
    }
}