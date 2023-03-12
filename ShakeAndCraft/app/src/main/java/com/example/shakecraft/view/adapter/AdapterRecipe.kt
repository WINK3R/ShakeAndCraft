package com.example.shakecraft.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.R
import com.example.shakecraft.model.Recipe



class AdapterRecipe(private val recipelist: List<Recipe>) : RecyclerView.Adapter<AdapterRecipe.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        var imageView: ImageView


        init {
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.item_name)
            imageView = view.findViewById(R.id.item_image)
        }
        fun bind(recipe: Recipe) {
            textView.text = recipe.item.name
            imageView.setImageResource(recipe.item.image)
        }
    }

    override fun getItemCount() = recipelist.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val recipe : Recipe = recipelist[position]
        viewHolder.bind(recipe)
    }




}

