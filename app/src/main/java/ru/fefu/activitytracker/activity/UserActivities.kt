package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.UserActivitiesBinding
import ru.fefu.activitytracker.activity.adapters.UserListAdapter
import ru.fefu.activitytracker.activity.cards.ICard
import ru.fefu.activitytracker.activity.cards.UserCardsRepository

class UserActivities : Base<UserActivitiesBinding>(R.layout.user_activities) {

    private val repo = UserCardsRepository()
    private val usersListAdapter = UserListAdapter(repo.getUsersCards() as List<ICard>)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.usersRecyclerView){
            adapter = usersListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        usersListAdapter.setCardClickListener {
            val action = ActivitiesDirections.actionActivityMainFragmentToUserInfoFragment()
            findNavController().navigate(action)
        }
    }
}