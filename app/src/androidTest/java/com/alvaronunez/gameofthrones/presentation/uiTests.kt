package com.alvaronunez.gameofthrones.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.source.LocalDataSource
import com.alvaronunez.gameofthrones.data.source.RemoteDataSource
import com.alvaronunez.gameofthrones.mock.FakeLocalDataSource
import com.alvaronunez.gameofthrones.mock.FakeRemoteDataSource
import com.alvaronunez.gameofthrones.presentation.ui.activity.SplashActivity
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListNotEmpty
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem
import com.squareup.moshi.Moshi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTests: KoinTest {


    @get:Rule
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java, false, false)


    @Before
    fun setUp() {
        val testModule = module(override = true) {
            factory<RemoteDataSource> { FakeRemoteDataSource(Moshi.Builder().build(), androidApplication()) }
            factory<LocalDataSource> { FakeLocalDataSource(Moshi.Builder().build(), androidApplication()) }
        }
        loadKoinModules(testModule)
    }

    @Test
    fun checkCategories() {
        mActivityTestRule.launchActivity(null)

        assertListItemCount(R.id.categoriesRecycler, 3)
        assertDisplayedAtPosition(R.id.categoriesRecycler, 0, R.string.books)
        assertDisplayedAtPosition(R.id.categoriesRecycler, 1, R.string.chars)
        assertDisplayedAtPosition(R.id.categoriesRecycler, 2, R.string.houses)

    }

    @Test
    fun checkBooks() {
        mActivityTestRule.launchActivity(null)
        clickListItem(R.id.categoriesRecycler, 0)

        assertListNotEmpty(R.id.listRecycler)
        assertDisplayed(R.id.bookName)
        assertDisplayed(R.id.bookAuthor)
        assertDisplayed(R.id.bookPages)
        assertDisplayed(R.id.bookPublisher)
        assertDisplayed(R.id.bookReleased)
    }

    @Test
    fun checkCharacters() {
        mActivityTestRule.launchActivity(null)
        clickListItem(R.id.categoriesRecycler, 1)

        assertListNotEmpty(R.id.listRecycler)
        assertDisplayed(R.id.charName)
        assertDisplayed(R.id.charPlayedBy)
        assertDisplayed(R.id.charBorn)
        assertDisplayed(R.id.charGender)
    }

    @Test
    fun checkHouses() {
        mActivityTestRule.launchActivity(null)
        clickListItem(R.id.categoriesRecycler, 2)

        assertListNotEmpty(R.id.listRecycler)
        assertDisplayed(R.id.houseName)
        assertDisplayed(R.id.houseTitle)
        assertDisplayed(R.id.houseRegion)
    }


}