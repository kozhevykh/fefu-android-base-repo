package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityTabsBinding
import  ru.fefu.activitytracker.activity.adapters.ViewAdapter

class Activities : Base<ActivityTabsBinding>(R.layout.activity_tabs) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewPager.adapter = ViewAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.text =
                if (position == 0) {
                    "Мои"
                } else
                    "Пользователей"
        }.attach()

        return binding.root
    }
} 