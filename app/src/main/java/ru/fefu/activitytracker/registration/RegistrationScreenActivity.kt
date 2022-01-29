package ru.fefu.activitytracker.registration

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.RegistrationScreenBinding

class RegistrationScreenActivity: AppCompatActivity() {
    private lateinit var binding: RegistrationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackButton.setOnClickListener {
            finish()
        }
    }
}