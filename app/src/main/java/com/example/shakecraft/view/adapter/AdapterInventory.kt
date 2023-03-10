package com.example.shakecraft.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.shakecraft.R
import com.example.shakecraft.model.Item
import com.example.shakecraft.view.viewholder.ViewHolderInventory


class AdapterInventory(private val inventory: List<Item>) : RecyclerView.Adapter<AdapterInventory.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.item_name)
        }
        fun bind(item: Item) {
            textView.text = item.name
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

