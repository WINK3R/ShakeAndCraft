package com.example.shakecraft.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.R
import com.example.shakecraft.model.Item



class AdapterInventory(private val inventory: List<Item>) : RecyclerView.Adapter<AdapterInventory.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val textViewNumber: TextView
        var imageView: ImageView


        init {
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.item_name)
            textViewNumber = view.findViewById(R.id.item_stock)
            imageView = view.findViewById(R.id.item_image)
        }
        fun bind(item: Item) {
            textView.text = item.type.name
            textViewNumber.text = item.stack.toString()
            imageView.setImageResource(item.type.image)
        }
    }

    override fun getItemCount() = inventory.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item : Item = inventory[position]
        viewHolder.bind(item)
    }




}

