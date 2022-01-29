package ru.fefu.activitytracker.map

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import org.osmdroid.util.GeoPoint
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.Nav_act
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.activity.Base
import ru.fefu.activitytracker.activity.room.ActivityRoom
import ru.fefu.activitytracker.activity.room.FinishTimeUpdate
import ru.fefu.activitytracker.activity.room.calc.toFormattedDistance
import ru.fefu.activitytracker.activity.room.calc.toTimerFormat
import ru.fefu.activitytracker.databinding.ActivityTrackBinding
import ru.fefu.activitytracker.map.cards.TypeCardName
import java.time.Duration
import java.time.LocalDateTime


class Track:
    Base<ActivityTrackBinding>(R.layout.activity_track) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activityId = requireArguments()["activityId"] as Int
        var reassembled = requireArguments()["reassembled"] as Boolean
        ActivityService.startForegroundTracking(requireContext(), activityId)

        val activityData: ActivityRoom = App.INSTANCE.db.activityDao().getById(activityId)

        binding.typeTitle.text = TypeCardName.valueOf(activityData.type.toString()).type
        App.INSTANCE.db.activityDao().getLiveById(activityId)
            .observe(viewLifecycleOwner) {
                if (it.coords.isNotEmpty() && !reassembled) {
                    val lastCoordinate = it.coords.last()
                    (activity as MapActivity).polyline.addPoint(
                        GeoPoint(lastCoordinate.first, lastCoordinate.second)
                    )
                } else if (it.coords.isNotEmpty() && reassembled) {
                    it.coords.forEach { coordinate ->
                        (activity as MapActivity).polyline.addPoint(
                            GeoPoint(coordinate.first, coordinate.second)
                        )
                    }
                    reassembled = false
                }
            }

        val uiJob = GlobalScope.launch {
            withContext(Dispatchers.IO) {
                while (true) {
                    activity?.runOnUiThread {
                        binding.durationTrack.text =
                            Duration.between(activityData.start, LocalDateTime.now())
                                .toTimerFormat()

                        binding.distanceTrack.text = ActivityService.distance.toFormattedDistance()

                    }
                    delay(1000L)
                }
            }
        }

        binding.finishButton.setOnClickListener {
            ActivityService.stopForegroundTracking(requireActivity(), activityId)

            App.INSTANCE.db.activityDao().updateFinishTime(
                FinishTimeUpdate(ActivityService.activityId, LocalDateTime.now())
            )

            uiJob.cancel()

            val intent = Intent(requireContext(), Nav_act::class.java)
            startActivity(intent)

            (it.parent as ViewGroup).removeView(it)

            (activity as MapActivity).finish()
            onDestroy()
            (activity as MapActivity).binding.backButton.setOnClickListener {
                val intent = Intent(requireContext(), Nav_act::class.java)
                startActivity(intent)
                (activity as MapActivity).finish()
                onDestroy()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}