package com.alvaronunez.gameofthrones.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvaronunez.gameofthrones.R
import com.alvaronunez.gameofthrones.data.models.HouseDTO
import com.alvaronunez.gameofthrones.presentation.ui.common.basicDiffUtil
import com.alvaronunez.gameofthrones.presentation.ui.common.inflate

class HousesAdapter :
        RecyclerView.Adapter<HousesAdapter.ViewHolder>() {

    var houses: List<HouseDTO> by basicDiffUtil(
            emptyList(),
            areItemsTheSame = { old, new -> old.name == new.name }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_house, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = houses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val house = houses[position]
        holder.bind(house)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(house: HouseDTO) {
            itemView.findViewById<TextView>(R.id.houseName).text = house.name
            itemView.findViewById<TextView>(R.id.houseTitle).text = house.title
            itemView.findViewById<TextView>(R.id.houseRegion).text = house.region
        }
    }
}