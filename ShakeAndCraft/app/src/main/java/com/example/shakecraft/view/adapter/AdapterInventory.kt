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
import com.example.shakecraft.model.Tool


class AdapterInventory(
    private val inventory: List<Item>,
    private val listener: OnItemLongClickListener,
    private val currentPlayer : Player
    ) :
    RecyclerView.Adapter<AdapterInventory.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnLongClickListener {
        val textView: TextView
        val textViewNumber: TextView
        var imageView: ImageView
        var armorIndicator : ImageView


        init {
            // Define click listener for the ViewHolder's View
            itemView.setOnLongClickListener(this)
            textView = view.findViewById(R.id.item_name)
            textViewNumber = view.findViewById(R.id.item_stock)
            imageView = view.findViewById(R.id.item_image)
            armorIndicator = view.findViewById(R.id.armor_indicator)
        }

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemLongClick(position)
            }
            return true
        }
        fun bind(item: Item) {
            textView.text = item.type.name
            textViewNumber.text = item.stack.toString()
            imageView.setImageResource(item.type.image)
            if (item is Tool) {
                println("tool")
                armorIndicator.visibility = View.VISIBLE
                if(currentPlayer.equipedItem == item) armorIndicator.setImageResource(R.drawable.armor_equiped_icon) else  armorIndicator.setImageResource(R.drawable.armor_icon)

            }
        }

    }

    interface OnItemLongClickListener{
        fun onItemLongClick(position: Int)
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

