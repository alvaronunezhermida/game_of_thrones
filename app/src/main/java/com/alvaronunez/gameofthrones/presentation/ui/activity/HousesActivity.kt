package com.alvaronunez.gameofthrones.presentation.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.models.HouseDTO
import com.alvaronunez.gameofthrones.presentation.ui.adapter.HousesAdapter
import com.alvaronunez.gameofthrones.presentation.ui.contract.HousesContract
import com.alvaronunez.gameofthrones.presentation.ui.presenter.HousesPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HousesActivity : AppCompatActivity(), HousesContract.View {

    private val presenter: HousesPresenter by inject { parametersOf(this) }

    private lateinit var adapter: HousesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setTitle(R.string.houses)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        presenter.onCreate()

        adapter = HousesAdapter()
        findViewById<RecyclerView>(R.id.listRecycler).adapter = adapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        findViewById<ProgressBar>(R.id.progress).visibility = View.VISIBLE
    }

    override fun hideProgress() {
        findViewById<ProgressBar>(R.id.progress).visibility = View.GONE
    }

    override fun loadHouses(houses: List<HouseDTO>) {
        adapter.houses = houses
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}