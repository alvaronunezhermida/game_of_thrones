package com.alvaronunez.gameofthrones.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.models.CategoryDTO
import com.alvaronunez.gameofthrones.presentation.ui.common.basicDiffUtil
import com.alvaronunez.gameofthrones.presentation.ui.common.inflate

class BooksAdapter(private val listener: (CategoryDTO) -> Unit) :
        RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    var categories: List<CategoryDTO> by basicDiffUtil(
            emptyList(),
            areItemsTheSame = { old, new -> old.name == new.name }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_category, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
        holder.itemView.setOnClickListener { listener(category) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: CategoryDTO) {
            itemView.findViewById<TextView>(R.id.categoryName).text = category.name
        }
    }
}