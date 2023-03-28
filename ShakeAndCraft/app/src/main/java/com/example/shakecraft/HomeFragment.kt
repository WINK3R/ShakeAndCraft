package com.example.shakecraft
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.shakecraft.model.Player


class HomeFragment : Fragment() {
    private lateinit var pseudoEditText : EditText
    private lateinit var progressbar : ProgressBar
    private lateinit var level : TextView
    private lateinit var rank : TextView
    private lateinit var maxXp : TextView
    private lateinit var xp : TextView
    private lateinit var buttonCollect : ConstraintLayout
    private lateinit var buttonBoss : ConstraintLayout
    private lateinit var buttonForge : ConstraintLayout
    private lateinit var playermage : ImageView
    private lateinit var equipeditem: ImageView
    private lateinit var eventFishing: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentPlayer = (activity as MainActivity).currentPlayer
        val isRaining = (activity as MainActivity).isRaining
        val view = inflater.inflate(R.layout.fragment_home,container,false)

        // Initialize views
        initializeViews(view, currentPlayer, isRaining)

        return view
    }

    /*fun loadWeatherDate(){
        CoroutineScope(Dispatchers.IO).launch {
            val
        }
    }*/
    private fun initializeViews(view: View, currentPlayer : Player, isRaining : Boolean) {
        progressbar = view.findViewById(R.id.levelProgressBar)
        level = view.findViewById(R.id.levelTextView)
        rank = view.findViewById(R.id.rankTextView)
        maxXp = view.findViewById(R.id.maxXpTextView)
        xp = view.findViewById(R.id.xpTextView)
        playermage = view.findViewById(R.id.playerImage)
        buttonCollect = view.findViewById(R.id.buttonCollect)
        equipeditem = view.findViewById(R.id.equipedItemAttack)
        eventFishing = view.findViewById(R.id.buttonFishing)
        buttonCollect.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_collectFragment, null, NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build())
        }
        buttonBoss = view.findViewById(R.id.buttonBoss)
        buttonBoss.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_bossFragment, null, NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build())
        }
        buttonForge = view.findViewById(R.id.buttonForge)
        buttonForge.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_forgeFragment, null, NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build())
        }
        level.text = currentPlayer.level.toString()
        rank.text = currentPlayer.rank
        xp.text = currentPlayer.xp.toString()
        maxXp.text = (currentPlayer.level*100).toString()
        progressbar.progress = currentPlayer.xp
        progressbar.max = currentPlayer.level*100
        playermage.setImageResource(currentPlayer.image)
        if(currentPlayer.equipedItem?.type?.image != null) equipeditem.setImageResource(
            currentPlayer.equipedItem!!.type.image)
        if(isRaining == true){
            eventFishing.visibility = View.VISIBLE

        }


        pseudoEditText = view.findViewById(R.id.pseudoEditText)

        pseudoEditText.setText(currentPlayer.pseudo)
        pseudoEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentPlayer.pseudo = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


}