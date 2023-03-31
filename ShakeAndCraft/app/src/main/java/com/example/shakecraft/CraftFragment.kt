package com.example.shakecraft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.model.Player
import com.example.shakecraft.model.Recipe
import com.example.shakecraft.model.RecipeManager
import com.example.shakecraft.view.adapter.AdapterMaterials
import com.example.shakecraft.viewmodel.MainViewModel


class CraftFragment : Fragment() {
    private lateinit var buttonBack: TextView
    private lateinit var recyclerViewMaterials: RecyclerView
    private lateinit var recipe: Recipe
    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var buttonForge: Button
    private lateinit var buttonForgeMax: Button
    private lateinit var numberCraftable: TextView
    val viewModel : MainViewModel by activityViewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_craft, container, false)
        recipe = arguments?.getParcelable("recipe")!!

        initializeViews(view, viewModel.currentPlayer)
        setUpRecyclerView(view,viewModel.currentPlayer)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setUpRecyclerView(view: View, currentPlayer: Player) {
        recyclerViewMaterials = view.findViewById(R.id.RecyclerviewMaterials)
        with(recyclerViewMaterials) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterMaterials(recipe.ingredients, currentPlayer)

        }

    }

    private fun initializeViews(view: View, currentPlayer: Player) {
        buttonBack = view.findViewById(R.id.backbutton)
        buttonForge = view.findViewById(R.id.buttonForge)
        buttonForgeMax = view.findViewById(R.id.buttonForgeMax)
        numberCraftable = view.findViewById(R.id.craftableNumber)

        buttonBack.setOnClickListener{
            findNavController().navigate(R.id.action_craftFragment_to_forgeFragment)
        }
        image = view.findViewById(R.id.item_image)
        name = view.findViewById(R.id.item_name)
        image.setImageResource(recipe.item.type.image)
        name.text = recipe.item.type.name
        buttonForge.isEnabled = RecipeManager.isCraftable(recipe,currentPlayer)
        buttonForgeMax.isEnabled = RecipeManager.isCraftable(recipe,currentPlayer)
        buttonForgeMax.text = "CRAFT MAX (${RecipeManager.HowManyCraftable(recipe, currentPlayer)})"
        numberCraftable.text = RecipeManager.HowManyCraftable(recipe,currentPlayer).toString()

        buttonForge.setOnClickListener{
            currentPlayer.craft(recipe)
            initializeViews(view, currentPlayer)
            setUpRecyclerView(view, currentPlayer)
        }
        buttonForgeMax.setOnClickListener{
            currentPlayer.craft(recipe, RecipeManager.HowManyCraftable(recipe, currentPlayer))
            initializeViews(view, currentPlayer)
            setUpRecyclerView(view, currentPlayer)
        }


    }

}