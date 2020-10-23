package com.alvaronunez.gameofthrones.di

import android.app.Application
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.repository.Repository
import com.alvaronunez.gameofthrones.data.source.LocalDataSource
import com.alvaronunez.gameofthrones.data.source.RemoteDataSource
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.presentation.data.database.AppDatabase
import com.alvaronunez.gameofthrones.presentation.data.database.RoomDataSource
import com.alvaronunez.gameofthrones.presentation.data.service.Service
import com.alvaronunez.gameofthrones.presentation.data.service.ServiceDataSource
import com.alvaronunez.gameofthrones.presentation.ui.presenter.CategoriesPresenter
import com.alvaronunez.gameofthrones.presentation.ui.presenter.SplashPresenter
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger(Level.NONE)
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, domainModule, presentersModule))
    }
}

private val appModule = module {
    single(named("baseApiUrl")) { androidApplication().getString(R.string.baseApiUrl) }
    single { AppDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory { Service(get(named("baseApiUrl"))) }
    factory<RemoteDataSource> { ServiceDataSource(get()) }
}

private val dataModule = module {
    factory { Repository(get(), get()) }
}

private val domainModule = module {
    factory { GetCategories(get()) }
}

private val presentersModule = module {
    factory { SplashPresenter(get()) }
    factory { CategoriesPresenter() }
}