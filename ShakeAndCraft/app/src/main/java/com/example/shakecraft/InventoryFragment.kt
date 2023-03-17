package com.example.shakecraft
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.model.Player
import com.example.shakecraft.model.Tool
import com.example.shakecraft.view.adapter.AdapterInventory


class InventoryFragment() : Fragment( ), AdapterInventory.OnItemLongClickListener {
    private lateinit var currentPlayer: Player
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onItemLongClick(position: Int) {
        if(currentPlayer.items[position] is Tool) {
            if (currentPlayer.equipeItem(currentPlayer.items[position]) == true)

                Toast.makeText(
                    context,
                    currentPlayer.items[position].type.name + " was well equipped",
                    Toast.LENGTH_SHORT
                ).show()
            else
                Toast.makeText(
                    context,
                    currentPlayer.items[position].type.name + " has been well unequipped",
                    Toast.LENGTH_SHORT
                ).show()
            setUpRecyclerView(view?.parent as ViewGroup, this)
        }
    }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentPlayer = (activity as MainActivity).currentPlayer
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        // Initialize views
        setUpRecyclerView(view, this)

        return view
    }
    private fun setUpRecyclerView(view: View,listener: AdapterInventory.OnItemLongClickListener ) {
        recyclerView = view.findViewById(R.id.recyclerviewInventory)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterInventory(currentPlayer.items, listener , currentPlayer)
        }
    }


}