package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.PersonalActivityInfoBinding

class PersonalCardInfo : Base<PersonalActivityInfoBinding>(R.layout.personal_activity_info) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myInfoToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

} 