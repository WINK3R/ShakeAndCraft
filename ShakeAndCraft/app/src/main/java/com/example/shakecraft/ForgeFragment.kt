package com.example.shakecraft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.model.RecipeManager
import com.example.shakecraft.view.adapter.AdapterRecipe


class ForgeFragment : Fragment() {
    private lateinit var buttonBack: TextView
    private lateinit var recyclerViewObjects: RecyclerView
    private lateinit var recyclerViewTools: RecyclerView
    private lateinit var recyclerViewBlacksmithing: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun initializeViews(view: View) {
        buttonBack = view.findViewById<TextView>(R.id.backbutton)
        buttonBack.setOnClickListener{
            findNavController().navigate(R.id.action_forgeFragment_to_homeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forge, container, false)

        // Initialize views
        initializeViews(view)

        setUpRecyclerView(view)

        return view
    }

    private fun setUpRecyclerView(view: View) {
        recyclerViewObjects = view.findViewById(R.id.RecyclerviewObjects)
        with(recyclerViewObjects) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterRecipe(RecipeManager.recipeListObjects)
        }
        recyclerViewTools = view.findViewById(R.id.RecyclerviewTools)
        with(recyclerViewTools) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterRecipe(RecipeManager.recipeListTools)
        }
        recyclerViewBlacksmithing = view.findViewById(R.id.RecyclerviewBlacksmithing)
        with(recyclerViewBlacksmithing) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterRecipe(RecipeManager.recipeListBlacksmithing)
        }
    }


}