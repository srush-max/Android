package com.example.finalproject

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Animal: AppCompatActivity() {

    var isPlaying = false
    lateinit var mediaPlayer: MediaPlayer
    lateinit var seekBar: SeekBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animal)

        val lion_btn = findViewById<ImageButton>(R.id.lionid)
        val elephant_btn = findViewById<ImageButton>(R.id.elephantid)
        val horse_btn = findViewById<ImageButton>(R.id.horse)
        val monkey_btn = findViewById<ImageButton>(R.id.monkeyid)
        val dog_btn = findViewById<ImageButton>(R.id.dogid)
        val cow_btn = findViewById<ImageButton>(R.id.cowid)

        lion_btn.setOnClickListener {
            MediaPlayer.create(this, R.raw.lion).start()
            if (!isPlaying) {
                mediaPlayer = MediaPlayer.create(this, R.raw.lion)
                mediaPlayer.start()
                mediaPlayer.isLooping = true
                isPlaying = true
            } else {
                mediaPlayer.pause()
                isPlaying = false
            }
        }
        elephant_btn.setOnClickListener {
            MediaPlayer.create(this, R.raw.elephant).start()
            if (!isPlaying) {
                mediaPlayer = MediaPlayer.create(this, R.raw.elephant)
                mediaPlayer.start()
                mediaPlayer.isLooping = true
                isPlaying = true
            } else {
                mediaPlayer.pause()
                isPlaying = false
            }
        }
        horse_btn.setOnClickListener {
            MediaPlayer.create(this, R.raw.horse).start()
            if (!isPlaying) {
                mediaPlayer = MediaPlayer.create(this, R.raw.horse)
                mediaPlayer.start()
                mediaPlayer.isLooping = true
                isPlaying = true
            } else {
                mediaPlayer.pause()
                isPlaying = false
            }
        }
        monkey_btn.setOnClickListener {
            MediaPlayer.create(this, R.raw.monkey).start()
            if (!isPlaying) {
                mediaPlayer = MediaPlayer.create(this, R.raw.monkey)
                mediaPlayer.start()
                mediaPlayer.isLooping = true
                isPlaying = true
            } else {
                mediaPlayer.pause()
                isPlaying = false
            }
        }
        dog_btn.setOnClickListener {
            MediaPlayer.create(this, R.raw.dog).start()
            if (!isPlaying) {
                mediaPlayer = MediaPlayer.create(this, R.raw.dog)
                mediaPlayer.start()
                mediaPlayer.isLooping = true
                isPlaying = true
            } else {
                mediaPlayer.pause()
                isPlaying = false
            }
        }
        cow_btn.setOnClickListener {
            MediaPlayer.create(this, R.raw.cow).start()
            if (!isPlaying) {
                mediaPlayer = MediaPlayer.create(this, R.raw.cow)
                mediaPlayer.start()
                mediaPlayer.isLooping = true
                isPlaying = true
            } else {
                mediaPlayer.pause()
                isPlaying = false
            }
        }

    }
}