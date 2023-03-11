package com.example.shakecraft

import android.annotation.SuppressLint
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
import com.example.shakecraft.data.Stub
import com.example.shakecraft.model.Boss
import com.example.shakecraft.model.Generator
import com.example.shakecraft.model.Player
import kotlin.math.pow
import kotlin.math.sqrt


class BossFragment(var player: Player) : Fragment() {

    var stubdata = Stub().load();
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
        // Récupérez une référence à la ProgressBar dans la vue
        val view = inflater.inflate(R.layout.fragment_boss, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        image = view.findViewById(R.id.imageBoss)
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        var boss = Generator.generateBoss();
        progressBar.max = boss.maxlife;
        progressBar.progress = boss.life;
        image.setImageResource(boss.image)


        // Créez un écouteur de capteur d'accéléromètre pour écouter les secousses
        accelerometerEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Ne faites rien ici
            }

            @SuppressLint("ServiceCast")
            override fun onSensorChanged(event: SensorEvent?) {
                val acceleration = sqrt(
                    event!!.values[0].pow(2) + event.values[1].pow(2) + event.values[2].pow(2)
                )
                if(boss.life <= 0){
                    val item = Generator.generateLootBoss(boss.possibleLoot);
                    println(item);
                    player.addItem(item);
                    boss = Generator.generateBoss();
                    println(boss);
                    image.setImageResource(boss.image)
                    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(100)





                }
                if (acceleration > 40) {
                    // Le téléphone a été secoué, mettre à jour la barre de chargement ici
                    boss.takeDamage((acceleration/80).toInt());
                    progressBar.progress = boss.life;
                }
            }
        }

        // Enregistrez l'écouteur de capteur d'accéléromètre
        sensorManager.registerListener(
            accelerometerEventListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_GAME
        )


        // Retournez la vue de fragment
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