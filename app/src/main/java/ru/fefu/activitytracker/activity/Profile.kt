

package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityProfileBinding

class Profile : Base<ActivityProfileBinding>(R.layout.activity_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changePasswordBtn.setOnClickListener {
            val action = ProfileDirections.actionActivityProfileFragmentToChangePasswordFragment()
            findNavController().navigate(action)
        }
    }
}