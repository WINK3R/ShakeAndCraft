package com.example.shakecraft.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.shakecraft.R
import com.example.shakecraft.model.Item


class AdapterBossLoot(private val possibleLoot: List<Pair<Item, Double>>) : RecyclerView.Adapter<AdapterBossLoot.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val textViewDropRate: TextView
        var imageView: ImageView


        init {
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.item_name)
            textViewDropRate = view.findViewById(R.id.item_stock)
            imageView = view.findViewById(R.id.item_image)
        }
        fun bind(item: Pair<Item, Double>) {
            textView.text = item.first.type.name
            textViewDropRate.text = (item.second*100).toString()
            imageView.setImageResource(item.first.type.image)
        }
    }

    override fun getItemCount() = possibleLoot.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_loot, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item : Pair<Item, Double> = possibleLoot[position]
        viewHolder.bind(item)
    }




}

