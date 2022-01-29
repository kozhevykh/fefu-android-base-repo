package ru.fefu.activitytracker.map

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityTrackBinding
import ru.fefu.activitytracker.activity.Base

class Track : Base<ActivityTrackBinding>(R.layout.activity_track) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_track, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val finishButton: ImageButton = view.findViewById(R.id.finish_button)
        finishButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Track()
    }
}