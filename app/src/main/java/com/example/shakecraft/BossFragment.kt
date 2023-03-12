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
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.model.Generator
import com.example.shakecraft.view.adapter.AdapterBossLoot
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
        val imageView = view.findViewById<ImageView>(R.id.imageBoss)
        imageView.scaleX = 1.2f
        imageView.scaleY = 1.2f

// Créez une animation qui modifie la propriété scaleX et scaleY de l'image
        val scaleAnimation = ScaleAnimation(
            1.2f, // de 2.0 à 1.0
            1.0f,
            1.2f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        scaleAnimation.duration = 1000 // dure 1 seconde
        scaleAnimation.repeatCount = Animation.INFINITE // répéter indéfiniment
        scaleAnimation.repeatMode = Animation.REVERSE // inverser la direction de l'animation

// Appliquez l'animation à l'image
        imageView.startAnimation(scaleAnimation)


        progressBar = view.findViewById(R.id.progressBar)
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        var boss = Generator.generateBoss()
        progressBar.max = boss.maxlife
        progressBar.progress = boss.life
        imageView.setImageResource(boss.image)


        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerviewBossLoot)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterBossLoot(boss.possibleLoot)
        }


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
                    val maVue = view.findViewById<View>(R.id.toast)
                    val image = maVue.findViewById<ImageView>(R.id.imageViewLoot)
                    val name = maVue.findViewById<TextView>(R.id.nameLoot)
                    val xp = maVue.findViewById<TextView>(R.id.xpRewarded)
                    maVue.visibility = View.VISIBLE
                    image.setImageResource(item.image)
                    name.text = item.name
                    xp.text = boss.xpReward.toString()
                    maVue.postDelayed({
                        maVue.visibility = View.GONE

                    }, 3000)
                    boss = Generator.generateBoss()
                    println(boss)
                    imageView.setImageResource(boss.image)
                    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(100)
                    progressBar.max = boss.maxlife
                    with(recyclerView) {
                        layoutManager = LinearLayoutManager(view.context)
                        adapter = AdapterBossLoot(boss.possibleLoot)
                    }


                    // Définissez la propriété scaleX et scaleY de l'image sur 0.5f






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