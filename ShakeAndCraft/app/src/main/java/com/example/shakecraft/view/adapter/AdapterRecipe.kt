package com.example.shakecraft.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.MainActivity
import com.example.shakecraft.R
import com.example.shakecraft.model.Recipe



class AdapterRecipe(
    private val recipelist: List<Recipe>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<AdapterRecipe.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView
        var imageView: ImageView


        init {
            // Define click listener for the ViewHolder's View
            itemView.setOnClickListener(this)
            textView = view.findViewById(R.id.item_name)
            imageView = view.findViewById(R.id.item_image)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val type = recipelist.first().type
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, type)
            }
        }
        fun bind(recipe: Recipe) {
            textView.text = recipe.item.name
            imageView.setImageResource(recipe.item.image)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int, type: String)
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

