package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.activity.adapters.ListItemAdapter
import ru.fefu.activitytracker.databinding.UserActivitiesBinding

class UserActivities : Base<UserActivitiesBinding>(R.layout.user_activities) {

    private val usersListAdapter = ListItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.usersRecyclerView){
            adapter = usersListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        usersListAdapter.serItemClickListener {
            val action = ActivitiesDirections.actionActivityMainFragmentToUserInfoFragment()
            findNavController().navigate(action)
        }
    }
}