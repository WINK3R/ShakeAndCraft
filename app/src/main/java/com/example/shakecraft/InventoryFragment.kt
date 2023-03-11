package com.example.shakecraft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.data.Stub
import com.example.shakecraft.model.Player
import com.example.shakecraft.view.adapter.AdapterInventory


class InventoryFragment(var player: Player) : Fragment( ) {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_inventory, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerviewInventory)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterInventory(player.items)
        }
        return view
    }

}