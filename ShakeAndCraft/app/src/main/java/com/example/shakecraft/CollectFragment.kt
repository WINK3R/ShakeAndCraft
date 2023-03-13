package com.example.shakecraft


import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import androidx.navigation.fragment.findNavController

import com.example.shakecraft.model.Generator
import com.example.shakecraft.model.Item
import com.example.shakecraft.model.Player


import kotlin.math.pow
import kotlin.math.sqrt


class CollectFragment() : Fragment() {
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var accelerometerEventListener: SensorEventListener
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonBack: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentPlayer = (activity as MainActivity).currentPlayer
        val view = inflater.inflate(R.layout.fragment_collect, container, false)

        // Initialize views
        initializeViews(view)

        // Set up accelerometer listener
        setUpAccelerometerListener(view,currentPlayer)

        // Set up activity orientation
        setUpActivityOrientation()

        // Return fragment view
        return view
    }
    private fun initializeViews(view: View) {
        progressBar = view.findViewById(R.id.progressBar)
        buttonBack = view.findViewById<TextView>(R.id.backbutton)
        buttonBack.setOnClickListener{
            findNavController().navigate(R.id.action_collectFragment_to_homeFragment)
        }
    }
    private fun setUpActivityOrientation(){
        val activity = requireActivity()
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    private fun  displayToast(view: View, item: Item){
        val maVue = view.findViewById<View>(R.id.toast)
        val image = maVue.findViewById<ImageView>(R.id.imageViewLoot)
        val name = maVue.findViewById<TextView>(R.id.nameLoot)
        val xp = maVue.findViewById<TextView>(R.id.xpRewarded)
        maVue.visibility = View.VISIBLE
        image.setImageResource(item.type.image)
        name.text = item.type.name
        xp.text = item.type.xpReward.toString()
        maVue.postDelayed({
            maVue.visibility = View.GONE

        }, 3000)
    }
    private fun setUpAccelerometerListener(view: View, currentPlayer: Player) {
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        accelerometerEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Do nothing
            }
            override fun onSensorChanged(event: SensorEvent?) {
                val acceleration = sqrt(event!!.values[0].pow(2) + event.values[1].pow(2) + event.values[2].pow(2))
                if(progressBar.progress == 100){

                    //Vibration to signal collect of the resource
                    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(100)

                    // Generate a resource item and XP reward
                    val item = Generator.generateLootCollection()
                    currentPlayer.addItem(item)
                    currentPlayer.gainXp(item.type.xpReward)

                    //reset to 0 the progress bar
                    progressBar.progress = 0

                    // Show loot toast view for 3 seconds
                    displayToast(view,item)

                }
                if (acceleration > 40) {
                    // raise the progress bar based on acceleration value
                    progressBar.progress += (acceleration/20).toInt()
                }
            }
        }

        // Register accelerometer sensor earphone with manager
        sensorManager.registerListener(
            accelerometerEventListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_GAME
        )
    }
    override fun onDestroy() {
        super.onDestroy()

        // Unregister the accelerometer sensor listener when the fragment is destroyed
        sensorManager.unregisterListener(accelerometerEventListener)
        val activity = requireActivity()
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }
}