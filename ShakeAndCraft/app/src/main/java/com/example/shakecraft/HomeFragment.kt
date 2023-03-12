package com.example.shakecraft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.shakecraft.model.Player


class HomeFragment : Fragment() {
    private lateinit var pseudo : TextView
    private lateinit var progressbar : ProgressBar
    private lateinit var level : TextView
    private lateinit var rank : TextView
    private lateinit var maxXp : TextView
    private lateinit var xp : TextView
    private lateinit var buttonCollect : ConstraintLayout
    private lateinit var buttonBoss : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentPlayer = (activity as MainActivity).currentPlayer
        val view = inflater.inflate(R.layout.fragment_home,container,false)

        // Initialize views
        initializeViews(view, currentPlayer)

        return view
    }
    private fun initializeViews(view: View, currentPlayer : Player) {
        pseudo = view.findViewById(R.id.pseudoTextView)
        progressbar = view.findViewById(R.id.levelProgressBar)
        level = view.findViewById(R.id.levelTextView)
        rank = view.findViewById(R.id.rankTextView)
        maxXp = view.findViewById(R.id.maxXpTextView)
        xp = view.findViewById(R.id.xpTextView)
        buttonCollect = view.findViewById(R.id.buttonCollect)
        buttonCollect.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_collectFragment, null, NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build())
        }
        buttonBoss = view.findViewById(R.id.buttonBoss)
        buttonBoss.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_bossFragment, null, NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build())
        }
        pseudo.text = currentPlayer.pseudo
        level.text = currentPlayer.level.toString()
        rank.text = currentPlayer.rank
        xp.text = currentPlayer.xp.toString()
        maxXp.text = (currentPlayer.level*100).toString()
        progressbar.progress = currentPlayer.xp
        progressbar.max = currentPlayer.level*100
    }


}