package com.alvaronunez.gameofthrones.presentation.ui.contract

class SplashContract {

    interface View {
        fun navigateToCategories()
        fun showError()
    }

    interface Presenter {
        fun onCreate()
        fun onDestroy()
    }

}