package com.example.shakecraft

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class PlusFragment : Fragment() {
    private lateinit var buttonWiki : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_plus, container, false)

        // Initialize views
        setUpRecyclerView(view)

        return view
    }

    private fun setUpRecyclerView(view: View,) {
        buttonWiki = view.findViewById(R.id.wikiButton)
        buttonWiki.setOnClickListener{
            val url = "https://codefirst.iut.uca.fr/git/lucas.delanier/ShakeAndCraft"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            intent.setPackage("com.android.chrome")
            startActivity(intent)
        }
    }


}