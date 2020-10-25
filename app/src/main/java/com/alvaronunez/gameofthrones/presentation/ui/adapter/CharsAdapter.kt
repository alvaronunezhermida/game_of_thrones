package com.alvaronunez.gameofthrones.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.models.CharDTO
import com.alvaronunez.gameofthrones.presentation.ui.common.basicDiffUtil
import com.alvaronunez.gameofthrones.presentation.ui.common.inflate

class CharsAdapter :
        RecyclerView.Adapter<CharsAdapter.ViewHolder>() {

    var chars: List<CharDTO> by basicDiffUtil(
            emptyList(),
            areItemsTheSame = { old, new -> old.name == new.name }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_char, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = chars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val char = chars[position]
        holder.bind(char)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(char: CharDTO) {
            itemView.findViewById<TextView>(R.id.charName).text = char.name
        }
    }
}