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




class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val player = (activity as MainActivity).currentPlayer

        val view = inflater.inflate(R.layout.fragment_home,container,false)
        val buttonCollect = view.findViewById<ConstraintLayout>(R.id.buttonCollect)
        buttonCollect.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_collectFragment, null, NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build())
        }
        val buttonBoss = view.findViewById<ConstraintLayout>(R.id.buttonBoss)
        buttonBoss.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_bossFragment, null, NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build())
        }

        val pseudo = view.findViewById<TextView>(R.id.pseudoTextView)
        val progressbar = view.findViewById<ProgressBar>(R.id.levelProgressBar)
        val level = view.findViewById<TextView>(R.id.levelTextView)
        val rank = view.findViewById<TextView>(R.id.rankTextView)
        val maxXp = view.findViewById<TextView>(R.id.maxXpTextView)
        val xp = view.findViewById<TextView>(R.id.xpTextView)
        pseudo.text = player.pseudo
        level.text = player.level.toString()
        rank.text = player.rank
        xp.text = player.xp.toString()
        maxXp.text = (player.level*100).toString()
        progressbar.progress = player.xp
        progressbar.max = player.level*100

        return view
    }


}