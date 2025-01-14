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
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shakecraft.model.Boss
import com.example.shakecraft.model.Generator
import com.example.shakecraft.model.Item
import com.example.shakecraft.model.Player
import com.example.shakecraft.view.adapter.AdapterBossLoot
import com.example.shakecraft.viewmodel.MainViewModel
import kotlin.math.pow
import kotlin.math.sqrt


class BossFragment() : Fragment() {

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var accelerometerEventListener: SensorEventListener
    private lateinit var progressBar: ProgressBar
    private lateinit var image: ImageView
    private lateinit var buttonCollect: TextView
    private lateinit var recyclerView: RecyclerView
    val viewModel : MainViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_boss, container, false)

        // Initialize views
        initializeViews(view)

        // Set up boss
        if(!viewModel.isBossInitialized)
            viewModel.currentBoss = Generator.generateBoss()
        setUpBoss(viewModel.currentBoss)

        // Set up RecyclerView for boss loot
        setUpRecyclerView(view)

        // Set up accelerometer listener
        viewModel.currentPlayer.value?.let { setUpAccelerometerListener(view, it) }


        // Set up activity orientation
        setUpActivityOrientation()

        // Return fragment view
        return view
    }
    private fun initializeViews(view: View) {
        progressBar = view.findViewById(R.id.progressBar)
        image = view.findViewById(R.id.imageBoss)
        buttonCollect = view.findViewById<TextView>(R.id.backbutton)
        buttonCollect.setOnClickListener{
            findNavController().navigate(R.id.action_bossFragment_to_homeFragment)
        }
    }
    private fun setUpBoss(boss: Boss) {
        progressBar.max = boss.maxlife
        progressBar.progress = boss.life.toInt()
        image.setImageResource(boss.image)

        // Create scale animation for boss image
        val scaleAnimation = ScaleAnimation(
            1.2f, // from 1.2 to 1.0
            1.0f,
            1.2f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnimation.duration = 1000 // lasts 1 second
        scaleAnimation.repeatCount = Animation.INFINITE // repeat indefinitely
        scaleAnimation.repeatMode = Animation.REVERSE // reverse animation direction
        image.startAnimation(scaleAnimation)
    }
    private fun setUpRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerviewBossLoot)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = AdapterBossLoot(viewModel.currentBoss.possibleLoot)
        }
    }
    private fun setUpActivityOrientation(){
        val activity = requireActivity()
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    private fun displayToast(view: View, item: Item){
        val toastView = view.findViewById<View>(R.id.toast)
        val lootImage = toastView.findViewById<ImageView>(R.id.imageViewLoot)
        val lootName = toastView.findViewById<TextView>(R.id.nameLoot)
        val xpReward = toastView.findViewById<TextView>(R.id.xpRewarded)
        toastView.visibility = View.VISIBLE
        lootImage.setImageResource(item.type.image)
        lootName.text = item.type.name
        xpReward.text = viewModel.currentBoss.xpReward.toString()
        toastView.postDelayed({
            toastView.visibility = View.GONE
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
                if (viewModel.currentBoss.life <= 0) {

                    //Vibration to signal the death of the boss
                    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(100)

                    // Generate a loot item and XP reward
                    val item = Generator.generateLootBoss(viewModel.currentBoss.possibleLoot)
                    currentPlayer.addItem(item)
                    currentPlayer.gainXp(viewModel.currentBoss.xpReward)

                    // Show loot toast view for 3 seconds
                    displayToast(view,item)

                    // Spawn new boss and reset progress bar
                    viewModel.currentBoss = Generator.generateBoss()
                    setUpBoss(viewModel.currentBoss)

                    //Update displayed information
                    setUpRecyclerView(view)

                } else {
                    if(acceleration > 20){
                        viewModel.currentBoss.takeDamage(((acceleration / 80)+ currentPlayer.attack()/100).toDouble())
                    }
                    else{
                        viewModel.currentBoss.takeDamage(currentPlayer.attack().toDouble()/100)
                    }
                    progressBar.progress = viewModel.currentBoss.life.toInt()

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