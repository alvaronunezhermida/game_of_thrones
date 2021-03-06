package com.alvaronunez.gameofthrones.di

import android.app.Application
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.repository.Repository
import com.alvaronunez.gameofthrones.data.source.LocalDataSource
import com.alvaronunez.gameofthrones.data.source.RemoteDataSource
import com.alvaronunez.gameofthrones.domain.usecases.GetBooks
import com.alvaronunez.gameofthrones.domain.usecases.GetCategories
import com.alvaronunez.gameofthrones.domain.usecases.GetCharacters
import com.alvaronunez.gameofthrones.domain.usecases.GetHouses
import com.alvaronunez.gameofthrones.presentation.data.database.AppDatabase
import com.alvaronunez.gameofthrones.presentation.data.database.RoomDataSource
import com.alvaronunez.gameofthrones.presentation.data.service.Service
import com.alvaronunez.gameofthrones.presentation.data.service.ServiceDataSource
import com.alvaronunez.gameofthrones.presentation.ui.contract.*
import com.alvaronunez.gameofthrones.presentation.ui.presenter.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    single(named("baseApiUrl")) { androidApplication().getString(R.string.base_api_url) }
    single { AppDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory { Service(get(named("baseApiUrl"))) }
    factory<RemoteDataSource> { ServiceDataSource(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

private val dataModule = module {
    factory { Repository(get(), get()) }
}

private val domainModule = module {
    factory { GetCategories(get()) }
    factory { GetBooks(get()) }
    factory { GetCharacters(get()) }
    factory { GetHouses(get()) }
}

private val presentersModule = module {
    factory { (view: SplashContract.View) -> SplashPresenter(view, get(), get()) }
    factory { (view: CategoriesContract.View) -> CategoriesPresenter(view, get(), get()) }
    factory { (view: BooksContract.View) -> BooksPresenter(view, get(), get()) }
    factory { (view: CharsContract.View) -> CharsPresenter(view, get(), get()) }
    factory { (view: HousesContract.View) -> HousesPresenter(view, get(), get()) }
}