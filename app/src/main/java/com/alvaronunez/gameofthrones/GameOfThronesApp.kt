package com.alvaronunez.gameofthrones

import android.app.Application
import com.alvaronunez.gameofthrones.di.initDI

class GameOfThronesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}