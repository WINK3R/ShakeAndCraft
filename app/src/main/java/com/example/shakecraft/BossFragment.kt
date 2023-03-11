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
import kotlin.math.pow
import kotlin.math.sqrt


class BossFragment() : Fragment() {


    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var accelerometerEventListener: SensorEventListener
    private lateinit var progressBar: ProgressBar
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val player = (activity as MainActivity).currentPlayer

        val view = inflater.inflate(R.layout.fragment_boss, container, false)
        val buttonCollect = view.findViewById<TextView>(R.id.backbutton)
        buttonCollect.setOnClickListener{
            findNavController().navigate(R.id.action_bossFragment_to_homeFragment)
        }


        progressBar = view.findViewById(R.id.progressBar)
        image = view.findViewById(R.id.imageBoss)
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        var boss = Generator.generateBoss()
        progressBar.max = boss.maxlife
        progressBar.progress = boss.life
        image.setImageResource(boss.image)


        // Créez un écouteur de capteur d'accéléromètre pour écouter les secousses
        accelerometerEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Ne faites rien ici
            }

            override fun onSensorChanged(event: SensorEvent?) {
                val acceleration = sqrt(
                    event!!.values[0].pow(2) + event.values[1].pow(2) + event.values[2].pow(2)
                )
                if(boss.life <= 0){
                    val item = Generator.generateLootBoss(boss.possibleLoot)
                    println(item)
                    player.addItem(item)
                    player.gainXp(boss.xpReward)
                    boss = Generator.generateBoss()
                    println(boss)
                    image.setImageResource(boss.image)
                    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(100)





                }
                if (acceleration > 40) {
                    boss.takeDamage((acceleration/80).toInt())
                    progressBar.progress = boss.life
                }
            }
        }


        sensorManager.registerListener(
            accelerometerEventListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_GAME
        )



        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity = requireActivity()
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    override fun onDestroy() {
        super.onDestroy()

        // Désenregistrez l'écouteur de capteur d'accéléromètre lorsque le fragment est détruit
        sensorManager.unregisterListener(accelerometerEventListener)
        val activity = requireActivity()
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }
}