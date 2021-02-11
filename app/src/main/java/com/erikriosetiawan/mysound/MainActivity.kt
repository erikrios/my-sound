package com.erikriosetiawan.mysound

import android.media.SoundPool
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.erikriosetiawan.mysound.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var soundPool: SoundPool
    private var soundId: Int = 0
    private var soundPoolLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        soundPool = SoundPool.Builder()
            .setMaxStreams(10)
            .build()

        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            if (status == 0) {
                soundPoolLoaded = true
            } else {
                Toast.makeText(this@MainActivity, "Gagal load", Toast.LENGTH_SHORT).show()
            }
        }

        soundId = soundPool.load(this, R.raw.clicking_glasses, 1)

        binding.btnSoundpool.setOnClickListener {
            if (soundPoolLoaded) {
                soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
            }
        }
    }
}