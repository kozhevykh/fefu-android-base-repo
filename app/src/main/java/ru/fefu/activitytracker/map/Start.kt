package ru.fefu.activitytracker.map

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityStartBinding
import ru.fefu.activitytracker.activity.Base
import ru.fefu.activitytracker.map.adapters.TypeListAdapter
import ru.fefu.activitytracker.map.cards.TypeCard
import ru.fefu.activitytracker.map.cards.TypeCardsRepository

class Start : Base<ActivityStartBinding>(R.layout.activity_start) {
    private val repo = TypeCardsRepository()
    private val typeListAdapter = TypeListAdapter(repo.getTypeCards() as List<TypeCard>)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.typeRecyclerView){
            adapter = typeListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.startButton.setOnClickListener {
            val action = StartDirections.actionActivityStartFragmentToActivityTrackFragment()
            findNavController().navigate(action)
        }
    }
}