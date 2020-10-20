package com.alvaronunez.gameofthrones.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvaronunez.gameofthrones.R
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity(), SplashPresenter.View {

    // TODO: 20/10/2020 Inject dependencies
    private val presenter by inject<SplashPresenter>()

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