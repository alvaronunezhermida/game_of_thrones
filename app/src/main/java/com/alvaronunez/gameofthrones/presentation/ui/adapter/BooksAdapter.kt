package com.alvaronunez.gameofthrones.presentation.ui.adapter

import android.text.format.DateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.models.BookDTO
import com.alvaronunez.gameofthrones.presentation.ui.common.basicDiffUtil
import com.alvaronunez.gameofthrones.presentation.ui.common.inflate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BooksAdapter() :
        RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    var books: List<BookDTO> by basicDiffUtil(
            emptyList(),
            areItemsTheSame = { old, new -> old.name == new.name }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_book, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(book: BookDTO) {
            itemView.findViewById<TextView>(R.id.bookName).text = book.name
            itemView.findViewById<TextView>(R.id.bookAuthor).text = itemView.context.getString(R.string.bookAuthor, book.authors?.get(0))
            itemView.findViewById<TextView>(R.id.bookPages).text = itemView.context.getString(R.string.bookPages, book.numberOfPages)
            itemView.findViewById<TextView>(R.id.bookPublisher).text = book.publisher
            itemView.findViewById<TextView>(R.id.bookReleased).text = DateTimeFormatter.ofPattern(itemView.context.getString(R.string.datePattern)).format(LocalDateTime.parse(book.released))
        }
    }
}