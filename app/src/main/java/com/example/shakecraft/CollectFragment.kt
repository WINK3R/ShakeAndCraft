package com.example.shakecraft

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.SENSOR_SERVICE
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
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.data.Stub
import com.example.shakecraft.model.GeneratorLoot
import com.example.shakecraft.model.Player
import com.example.shakecraft.view.adapter.AdapterInventory
import kotlin.math.pow
import kotlin.math.sqrt


class CollectFragment(var player: Player) : Fragment() {
    var stubdata = Stub().load();
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var accelerometerEventListener: SensorEventListener
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Récupérez une référence à la ProgressBar dans la vue
        val view = inflater.inflate(R.layout.fragment_collect, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

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
                if(progressBar.progress == 100){
                    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(100)



                    val item = GeneratorLoot.generateLootCollection();
                    println(item);

                    val maVue = view.findViewById<View>(R.id.toast)
                    val image = maVue.findViewById<ImageView>(R.id.imageViewLoot)
                    val name = maVue.findViewById<TextView>(R.id.nameLoot)
                    val xp = maVue.findViewById<TextView>(R.id.xpRewarded)
                    maVue.visibility = View.VISIBLE
                    val fadeIn = AlphaAnimation(0f, 1f)
                    fadeIn.duration = 200
                    maVue.startAnimation(fadeIn)
                    maVue.postDelayed({
                        maVue.visibility = View.GONE
                        image.setImageResource(item.image)
                        name.text = item.name

                    }, 3000)


                    player.addItem(item);
                    progressBar.progress = 0;
                }
                if (acceleration > 40) {
                    // Le téléphone a été secoué, mettre à jour la barre de chargement ici
                    progressBar.progress += (acceleration/20).toInt() // Incrémente la progression de la barre de 10 unités
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