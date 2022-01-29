package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.PassChangeBinding

class PassChange : Base<PassChangeBinding>(R.layout.pass_change) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarChangePassword.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
} 