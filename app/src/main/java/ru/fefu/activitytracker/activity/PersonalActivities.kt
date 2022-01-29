package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.PersonalActivitiesBinding
import ru.fefu.activitytracker.activity.adapters.MyListAdapter
import ru.fefu.activitytracker.activity.cards.ICard
import ru.fefu.activitytracker.activity.cards.PersonalCardsRepository

class PersonalActivities : Base<PersonalActivitiesBinding>(R.layout.personal_activities) {

    private val repo = PersonalCardsRepository()
    private val myListAdapter = MyListAdapter(repo.getMyCards() as List<ICard>)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.myRecyclerView){
            adapter = myListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        myListAdapter.setCardClickListener {
            val action = ActivitiesDirections.actionActivityMainFragmentToMyInfoFragment()
            findNavController().navigate(action)
        }
    }
}