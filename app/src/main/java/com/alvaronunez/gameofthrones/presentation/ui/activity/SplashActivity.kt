package com.alvaronunez.gameofthrones.presentation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.presentation.ui.contract.SplashContract
import com.alvaronunez.gameofthrones.presentation.ui.presenter.SplashPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private val presenter: SplashPresenter by inject{ parametersOf(this) }

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
        startActivity(Intent(this, CategoriesActivity::class.java))
        finish()
    }
}