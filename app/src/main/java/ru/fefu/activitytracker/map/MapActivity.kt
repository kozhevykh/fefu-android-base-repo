package ru.fefu.activitytracker.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityMapScreenBinding

class MapActivity : AppCompatActivity() {

    lateinit var binding: ActivityMapScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMapScreenBinding.inflate(layoutInflater)
        binding.backButton.setOnClickListener {
            finish()
        }
        setContentView(binding.root)


    }
}