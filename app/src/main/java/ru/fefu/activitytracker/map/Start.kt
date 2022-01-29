package ru.fefu.activitytracker.map

import android.os.Bundle
import android.view.View
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityStartBinding
import ru.fefu.activitytracker.activity.Base
import ru.fefu.activitytracker.activity.room.ActivityRoom
import ru.fefu.activitytracker.map.adapters.TypeListAdapter
import ru.fefu.activitytracker.map.cards.TypeCard
import ru.fefu.activitytracker.map.cards.TypeCardName
import java.time.LocalDateTime


class Start : Base<ActivityStartBinding>(R.layout.activity_start) {

       private val dataList = listOf(
        TypeCard(TypeCardName.BICYCLE.ordinal, false),
        TypeCard(TypeCardName.RUN.ordinal, false),
        TypeCard(TypeCardName.WALK.ordinal, false),
    )

    private var selectedActivity: TypeCardName? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TypeListAdapter(dataList)
        binding.typeRecyclerView.adapter = adapter

        adapter.setItemClickListener {
                position, activityType -> selectedActivity = activityType
            for(i in 0..binding.typeRecyclerView.layoutManager?.itemCount!!) {
                binding.typeRecyclerView.layoutManager?.findViewByPosition(i)?.isSelected=false
            }
            binding.typeRecyclerView.layoutManager?.findViewByPosition(position)?.isSelected=true
        }


        binding.startButton.setOnClickListener {
            selectedActivity?.let {
               App.INSTANCE.db.activityDao().insert(
                   ActivityRoom(
                       0,
                       selectedActivity!!.ordinal,
                       LocalDateTime.now().minusHours(2),
                       LocalDateTime.now(),
                       listOf(Pair(25.0, 25.0))
                   )
               )
                parentFragmentManager.beginTransaction().apply {
                    replace(
                        R.id.start_fragment_container,
                        Track(),
                        "start"
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }
}