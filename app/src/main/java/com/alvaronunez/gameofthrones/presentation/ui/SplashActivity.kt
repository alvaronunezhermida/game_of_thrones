package com.alvaronunez.gameofthrones.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvaronunez.gameofthrones.GameOfThronesApp
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.repository.Repository
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.data.database.RoomDataSource
import com.alvaronunez.gameofthrones.presentation.data.service.Service
import com.alvaronunez.gameofthrones.presentation.data.service.ServiceDataSource

class SplashActivity : AppCompatActivity(), SplashPresenter.View {

    // TODO: 20/10/2020 Inject dependencies
    private lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestory()
        super.onDestroy()
    }

    override fun navigateToCategories() {
        // TODO: 20/10/2020 Create and start CategoriesActivity
    }
}