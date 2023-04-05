package com.example.shakecraft.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.R
import com.example.shakecraft.model.Recipe
import com.example.shakecraft.model.RecipeManager
import com.example.shakecraft.view.adapter.AdapterRecipe


class ForgeFragment : Fragment(), AdapterRecipe.OnItemClickListener {
    private lateinit var buttonBack: TextView
    private lateinit var recyclerViewObjects: RecyclerView
    private lateinit var recyclerViewTools: RecyclerView
    private lateinit var recyclerViewBlacksmithing: RecyclerView
    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onItemClick(position: Int, type: String) {

        recipe = when(type){
            "Objects" -> RecipeManager.recipeListObjects[position]
            "Tools" -> RecipeManager.recipeListTools[position]
            "Blacksmithing" -> RecipeManager.recipeListBlacksmithing[position]
            else -> {RecipeManager.recipeListObjects[position]}
        }

        val bundle = bundleOf("recipe" to recipe)
        findNavController().navigate(R.id.action_forgeFragment_to_craftFragment, bundle)
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

        setUpRecyclerView(view, this)

        return view
    }

    private fun setUpRecyclerView(view: View, listener: AdapterRecipe.OnItemClickListener) {
        recyclerViewObjects = view.findViewById(R.id.RecyclerviewObjects)
        with(recyclerViewObjects) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterRecipe(RecipeManager.recipeListObjects, listener)
        }
        recyclerViewTools = view.findViewById(R.id.RecyclerviewTools)
        with(recyclerViewTools) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterRecipe(RecipeManager.recipeListTools, listener)
        }
        recyclerViewBlacksmithing = view.findViewById(R.id.RecyclerviewBlacksmithing)
        with(recyclerViewBlacksmithing) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterRecipe(RecipeManager.recipeListBlacksmithing, listener)
        }
    }


}