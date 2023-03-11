package com.example.shakecraft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)
        val buttonCollect = view.findViewById<ConstraintLayout>(R.id.buttonCollect)
        buttonCollect.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_collectFragment)
        }
        val buttonBoss = view.findViewById<ConstraintLayout>(R.id.buttonBoss)
        buttonBoss.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_bossFragment)
        }
        return view
    }


}