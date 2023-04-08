package com.example.shakecraft
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.model.Tool
import com.example.shakecraft.view.adapter.AdapterInventory
import com.example.shakecraft.viewmodel.MainViewModel


class InventoryFragment() : Fragment( ), AdapterInventory.OnItemLongClickListener {
    private lateinit var recyclerView: RecyclerView
    val viewModel : MainViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onItemLongClick(position: Int) {
        if (viewModel.currentPlayer.value!!.items[position] is Tool) {
            val text = if (viewModel.equipeItem(viewModel.currentPlayer.value!!.items[position]) ) " was well equipped" else " has been well unequipped"
            Toast.makeText(
                context,
                viewModel.currentPlayer.value!!.items[position].type.name + text,
                Toast.LENGTH_SHORT
            ).show()
            setUpRecyclerView(view?.parent as ViewGroup, this)
        }
    }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        // Initialize views
        setUpRecyclerView(view, this)

        return view
    }
    private fun setUpRecyclerView(view: View,listener: AdapterInventory.OnItemLongClickListener ) {
        recyclerView = view.findViewById(R.id.recyclerviewInventory)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = viewModel.currentPlayer.value?.let {
                AdapterInventory(viewModel.currentPlayer.value!!.items, listener ,
                    it
                )
            }
        }
    }



}