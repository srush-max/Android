package com.example.finalproject

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class Acoustic : AppCompatActivity() {

    private val mediaPlayerMap: MutableMap<Int, MediaPlayer> = mutableMapOf()
    private val seekBarMap: MutableMap<Int, SeekBar> = mutableMapOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acoustic)

        // Initialize buttons and seek bars
        val sweetvibesBtn = findViewById<ImageView>(R.id.svid)
        val gloomytoneBtn= findViewById<ImageView>(R.id.Gloomyid)
        val etheralBtn = findViewById<ImageView>(R.id.pipesmagic)
        val quietpianoBtn = findViewById<ImageView>(R.id.ppid)
        val oldletterBtn = findViewById<ImageView>(R.id.oldlid)
        val blissBtn = findViewById<ImageView>(R.id.Bliss)

        val sweetvibesSeekBar = findViewById<SeekBar>(R.id.sweetvibes_seekBar)
        val gloomytoneSeekBar = findViewById<SeekBar>(R.id.gloomytone_seekBar)
        val etheralSeekBar = findViewById<SeekBar>(R.id.ethereal_seekBar)
        val quietpianoSeekBar = findViewById<SeekBar>(R.id.quietp_seekBar)
        val oldletterSeekBar = findViewById<SeekBar>(R.id.letter_seekBar)
        val BlissSeekBar = findViewById<SeekBar>(R.id.bliss_seekBar)

        // Store seek bars in map for later control
        seekBarMap[R.raw.sweetv] = sweetvibesSeekBar
        seekBarMap[R.raw.gloomyt] = gloomytoneSeekBar
        seekBarMap[R.raw.ethyreal] = etheralSeekBar
        seekBarMap[R.raw.qp] = quietpianoSeekBar
        seekBarMap[R.raw.oldletter] = oldletterSeekBar
        seekBarMap[R.raw.blisss] = BlissSeekBar

        // Set click listeners for each button
        sweetvibesBtn.setOnClickListener { togglePlayback(R.raw.sweetv) }
        gloomytoneBtn.setOnClickListener { togglePlayback(R.raw.gloomyt) }
        etheralBtn.setOnClickListener { togglePlayback(R.raw.ethyreal) }
        quietpianoBtn.setOnClickListener { togglePlayback(R.raw.qp) }
        oldletterBtn.setOnClickListener { togglePlayback(R.raw.oldletter) }
        blissBtn.setOnClickListener { togglePlayback(R.raw.blisss) }

        // Initialize seek bars
        initializeSeekBars(sweetvibesSeekBar, gloomytoneSeekBar, etheralSeekBar, quietpianoSeekBar, oldletterSeekBar, BlissSeekBar)
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
