package com.example.shakecraft.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.R
import com.example.shakecraft.model.Item
import com.example.shakecraft.model.Player



class AdapterMaterials(private val materials: List<Item>, val currentplayer: Player) : RecyclerView.Adapter<AdapterMaterials.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val textViewNumberNeeded: TextView
        val textViewNumberPlayer: TextView
        var imageView: ImageView



        init {
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.item_name)
            textViewNumberPlayer = view.findViewById(R.id.numberCurrentPlayer)
            textViewNumberNeeded = view.findViewById(R.id.numberNeeded)
            imageView = view.findViewById(R.id.item_image)
        }
        fun bind(item: Item, currentplayer: Player) {
            textView.text = item.type.name
            val itemSearch = currentplayer.items.find { it.type.name == item.type.name }
            textViewNumberNeeded.text = item.stack.toString()
            textViewNumberPlayer.text = if (itemSearch != null) itemSearch.stack.toString() else  "0"

            imageView.setImageResource(item.type.image)
        }
    }

    override fun getItemCount() = materials.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_craft, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item : Item = materials[position]
        viewHolder.bind(item, currentplayer)
    }




}

