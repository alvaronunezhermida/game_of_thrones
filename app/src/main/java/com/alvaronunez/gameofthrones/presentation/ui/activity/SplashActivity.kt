package com.alvaronunez.gameofthrones.presentation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.presentation.ui.presenter.SplashPresenter
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity(), SplashPresenter.View {

    private val presenter by inject<SplashPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.onCreate(this)
    }

    override fun onDestroy() {
        presenter.onDestory()
        super.onDestroy()
    }

    override fun navigateToCategories() {
        startActivity(Intent(this, CategoriesActivity::class.java))
        finish()
    }
}