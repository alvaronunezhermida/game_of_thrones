package com.alvaronunez.gameofthrones.presentation.ui.contract

import com.alvaronunez.gameofthrones.data.models.CharDTO

class CharsContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError()
        fun loadChars(chars: List<CharDTO>)
    }

    interface Presenter {
        fun onCreate()
        fun onDestroy()
    }
}