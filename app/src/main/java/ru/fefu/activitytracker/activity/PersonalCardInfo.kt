package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.activity.cards.ListItem
import ru.fefu.activitytracker.activity.room.calc.getDistance
import ru.fefu.activitytracker.activity.room.calc.toFinishDateOrTime
import ru.fefu.activitytracker.activity.room.calc.toFormattedDurationBetween
import ru.fefu.activitytracker.activity.room.calc.toTime
import ru.fefu.activitytracker.databinding.PersonalActivityInfoBinding
import java.time.Duration

class PersonalCardInfo:
    Base<PersonalActivityInfoBinding>(R.layout.personal_activity_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val data = requireArguments().get("id") as Int
        val activity = App.INSTANCE.db.activityDao().getById(data)
        println(activity.id.toString() + " " + activity.type + " ")

        binding.myInfoToolbar.title = activity.type.type
        binding.distanceInfo.text = activity.coords.getDistance().toString()
        binding.durationInfo.text = Duration.between(activity.start, activity.finish).toFormattedDurationBetween()
        binding.pastTimeInfo.text = activity.start.toFinishDateOrTime()
        binding.startTimeInfo.text = activity.start.toTime()
        binding.finishTimeInfo.text = activity.finish?.toTime()

        binding.myInfoToolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }

        binding.myInfoToolbar.setOnMenuItemClickListener {
            true
        }

        super.onViewCreated(view, savedInstanceState)
    }
}