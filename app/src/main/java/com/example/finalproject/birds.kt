package com.example.finalproject

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class birds : AppCompatActivity() {

    private val mediaPlayerMap: MutableMap<Int, MediaPlayer> = mutableMapOf()
    private val seekBarMap: MutableMap<Int, SeekBar> = mutableMapOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birds)

        // Initialize buttons and seek bars
        val peacockkbtn = findViewById<ImageView>(R.id.PEACOCK)
        val sparrowbtn= findViewById<ImageView>(R.id.SPARROW)
        val cuckoobtn = findViewById<ImageView>(R.id.CUCKOO)
        val crowbtn = findViewById<ImageView>(R.id.CROW)
        val parrotbtn = findViewById<ImageView>(R.id.PARROT)
        val duckbtn = findViewById<ImageView>(R.id.DUCK)

        val peacockbar = findViewById<SeekBar>(R.id.dseekbar)
        val sparrowbar = findViewById<SeekBar>(R.id.sseekbar1)
        val cuckoobar = findViewById<SeekBar>(R.id.cseekbar2)
        val crowbar = findViewById<SeekBar>(R.id.cseekbar3)
        val parrotbar = findViewById<SeekBar>(R.id.pseekbar4)
        val duckbar = findViewById<SeekBar>(R.id.dseekbar5)

        // Store seek bars in map for later control
        seekBarMap[R.raw.peacockkk] = peacockbar
        seekBarMap[R.raw.sparrow] = sparrowbar
        seekBarMap[R.raw.cuckooo] = cuckoobar
        seekBarMap[R.raw.crowww] = crowbar
        seekBarMap[R.raw.parrots] = parrotbar
        seekBarMap[R.raw.duckk] = duckbar

        // Set click listeners for each button
        peacockkbtn.setOnClickListener { togglePlayback(R.raw.sweetv) }
        sparrowbtn.setOnClickListener { togglePlayback(R.raw.gloomyt) }
        cuckoobtn.setOnClickListener { togglePlayback(R.raw.ethyreal) }
        crowbtn.setOnClickListener { togglePlayback(R.raw.qp) }
        parrotbtn.setOnClickListener { togglePlayback(R.raw.oldletter) }
        duckbtn.setOnClickListener { togglePlayback(R.raw.blisss) }

        // Initialize seek bars
        initializeSeekBars(peacockbar, sparrowbar, cuckoobar, crowbar, parrotbar, duckbar)
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
