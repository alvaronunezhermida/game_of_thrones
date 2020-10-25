package com.alvaronunez.gameofthrones.presentation.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.models.CharDTO
import com.alvaronunez.gameofthrones.presentation.ui.adapter.CharsAdapter
import com.alvaronunez.gameofthrones.presentation.ui.contract.CharsContract
import com.alvaronunez.gameofthrones.presentation.ui.presenter.CharsPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CharsActivity : AppCompatActivity(), CharsContract.View {

    private val presenter: CharsPresenter by inject { parametersOf(this) }

    private lateinit var adapter: CharsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setTitle(R.string.chars)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        presenter.onCreate()

        adapter = CharsAdapter()
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

    override fun loadChars(chars: List<CharDTO>) {
        adapter.chars = chars
    }

    override fun showError() {
        AlertDialog.Builder(this)
                .setTitle(R.string.error_title)
                .setMessage(R.string.error_description)
                .setPositiveButton(R.string.ok) { _, _ ->
                    finish()
                }
                .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}