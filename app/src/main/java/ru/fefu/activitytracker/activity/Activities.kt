package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityTabsBinding
import ru.fefu.activitytracker.activity.adapters.ViewAdapter
import ru.fefu.activitytracker.map.MapActivity


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchButtonBehaviour()


        val lastActivity = App.INSTANCE.db.activityDao().getLast()

        lastActivity?.let {
            lastActivity.finish?.let {

            }
        }
        binding.activityButton.setOnClickListener() {
            val action = ActivitiesDirections.actionActivityMainFragmentToActivityMap()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        switchButtonBehaviour()
    }

    private fun switchButtonBehaviour() {
        val lastActivity = App.INSTANCE.db.activityDao().getLast()

        lastActivity?.let {
            lastActivity.finish?.let {
                binding.activityButton.setOnClickListener(::newActivityStarterListener)
            } ?: run {
                binding.activityButton.setOnClickListener {
                    continueStartedActivity(lastActivity.id)
                }
            }
        } ?: run {
            binding.activityButton.setOnClickListener(::newActivityStarterListener)
        }
    }


    private fun newActivityStarterListener(view: View) {
        val direction = ActivitiesDirections.actionActivityMainFragmentToActivityMap()
        findNavController().navigate(direction)
    }
    private fun continueStartedActivity(activityId: Int) {
        val direction = ActivitiesDirections.actionActivityMainFragmentToActivityMap(
            activityId = activityId
        )
        findNavController().navigate(direction)
    }

}