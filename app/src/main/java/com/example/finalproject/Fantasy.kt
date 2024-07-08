package com.example.finalproject

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class
Fantasy : AppCompatActivity() {

    private val mediaPlayerMap: MutableMap<Int, MediaPlayer> = mutableMapOf()
    private val seekBarMap: MutableMap<Int, SeekBar> = mutableMapOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fantasy)

        // Initialize buttons and seek bars
        val waterfallBtn = findViewById<ImageView>(R.id.waterfall)
        val thunderBtn = findViewById<ImageView>(R.id.thunder)
        val lakeBtn = findViewById<ImageView>(R.id.calmlake)
        val carBtn = findViewById<ImageView>(R.id.car)
        val disawayBtn = findViewById<ImageView>(R.id.distantaway)
        val fireBtn = findViewById<ImageView>(R.id.fire)

        val waterfallSeekBar = findViewById<SeekBar>(R.id.waterfall_seekBar)
        val thunderSeekBar = findViewById<SeekBar>(R.id.thunder_seekBar)
        val lakeSeekBar = findViewById<SeekBar>(R.id.lake_seekBar)
        val carSeekBar = findViewById<SeekBar>(R.id.car_seekBar)
        val disawaySeekBar = findViewById<SeekBar>(R.id.disaway_seekBar)
        val fireSeekBar = findViewById<SeekBar>(R.id.fire_seekBar)

        // Store seek bars in map for later control
        seekBarMap[R.raw.wfall] = waterfallSeekBar
        seekBarMap[R.raw.thunderr] = thunderSeekBar
        seekBarMap[R.raw.lake] = lakeSeekBar
        seekBarMap[R.raw.cari] = carSeekBar
        seekBarMap[R.raw.disaway] = disawaySeekBar
        seekBarMap[R.raw.fireplace] = fireSeekBar

        // Set click listeners for each button
        waterfallBtn.setOnClickListener { togglePlayback(R.raw.wfall) }
        thunderBtn.setOnClickListener { togglePlayback(R.raw.thunderr) }
        lakeBtn.setOnClickListener { togglePlayback(R.raw.lake) }
        carBtn.setOnClickListener { togglePlayback(R.raw.cari) }
        disawayBtn.setOnClickListener { togglePlayback(R.raw.disaway) }
        fireBtn.setOnClickListener { togglePlayback(R.raw.fireplace) }

        // Initialize seek bars
        initializeSeekBars(waterfallSeekBar, thunderSeekBar, lakeSeekBar, carSeekBar, disawaySeekBar, fireSeekBar)
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
