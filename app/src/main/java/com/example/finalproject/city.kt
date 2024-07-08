package com.example.finalproject

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class city : AppCompatActivity() {

    private val mediaPlayerMap: MutableMap<Int, MediaPlayer> = mutableMapOf()
    private val seekBarMap: MutableMap<Int, SeekBar> = mutableMapOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        // Initialize buttons and seek bars
        val airbtn = findViewById<ImageView>(R.id.planeid)
        val constbtn= findViewById<ImageView>(R.id.constructionid)
        val ambubtn = findViewById<ImageView>(R.id.ambulanceid)
        val streetbtn = findViewById<ImageView>(R.id.streetid)
        val cafebtn = findViewById<ImageView>(R.id.cafeid)
        val trainbtn = findViewById<ImageView>(R.id.trainid)

        val airseekbar = findViewById<SeekBar>(R.id.air_seekbar)
        val consrseekbar = findViewById<SeekBar>(R.id.constructio_seekbar)
        val ambuseekbar = findViewById<SeekBar>(R.id.ambulance_seekbar)
        val streetseekbar = findViewById<SeekBar>(R.id.street_seekbar)
        val cafeseekbar = findViewById<SeekBar>(R.id.cafe_seekbar)
        val trainseekbar = findViewById<SeekBar>(R.id.train_seekbar)

        // Store seek bars in map for later control
        seekBarMap[R.raw.airplane] = airseekbar
        seekBarMap[R.raw.constructionn] = consrseekbar
        seekBarMap[R.raw.ambulance] = ambuseekbar
        seekBarMap[R.raw.busys] = streetseekbar
        seekBarMap[R.raw.cafen] = cafeseekbar
        seekBarMap[R.raw.trainnn] = trainseekbar

        // Set click listeners for each button
        airbtn.setOnClickListener { togglePlayback(R.raw.airplane) }
        constbtn.setOnClickListener { togglePlayback(R.raw.constructionn) }
        ambubtn.setOnClickListener { togglePlayback(R.raw.ambulance) }
        streetbtn.setOnClickListener { togglePlayback(R.raw.busys) }
        cafebtn.setOnClickListener { togglePlayback(R.raw.cafen) }
        trainbtn.setOnClickListener { togglePlayback(R.raw.trainnn) }

        // Initialize seek bars
        initializeSeekBars(airseekbar, consrseekbar, ambuseekbar, streetseekbar, cafeseekbar, trainseekbar)
    }

    private fun togglePlayback(resId: Int) {
        if (mediaPlayerMap.containsKey(resId)) {
            val player = mediaPlayerMap[resId]
            if (player?.isPlaying == true) {
                player.pause()
            } else {
                player?.start()
            }
        } else {
            createMediaPlayer(resId)
        }
    }

    private fun createMediaPlayer(resId: Int) {
        val mediaPlayer = MediaPlayer.create(this, resId)
        mediaPlayer.isLooping = true
        mediaPlayerMap[resId] = mediaPlayer

        val seekBar = seekBarMap[resId]
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val volume = progress / 100.0f
                mediaPlayer.setVolume(volume, volume)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        mediaPlayer.start()
    }

    private fun initializeSeekBars(vararg seekBars: SeekBar) {
        for (seekBar in seekBars) {
            seekBar.progress = 50  // Set initial progress to 50% volume
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerMap.values.forEach { it.release() }
        mediaPlayerMap.clear()
    }
}
