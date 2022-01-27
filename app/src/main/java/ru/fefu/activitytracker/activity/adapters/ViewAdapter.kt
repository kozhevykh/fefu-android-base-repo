package ru.fefu.activitytracker.activity.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.activitytracker.activity.UserActivities
import ru.fefu.activitytracker.activity.PersonalActivities
import androidx.lifecycle.Lifecycle
import androidx.fragment.app.FragmentManager

class ViewAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            PersonalActivities()
        } else
            UserActivities()
    }
} 