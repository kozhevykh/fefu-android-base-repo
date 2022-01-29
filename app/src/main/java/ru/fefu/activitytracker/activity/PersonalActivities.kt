package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.activity.adapters.ListItemAdapter
import ru.fefu.activitytracker.activity.cards.ListItem
import ru.fefu.activitytracker.activity.room.calc.toDateSeparator
import ru.fefu.activitytracker.databinding.PersonalActivitiesBinding

class PersonalActivities : Base<PersonalActivitiesBinding>(R.layout.personal_activities) {

    private val myListAdapter = ListItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.myRecyclerView){
            adapter = myListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        myListAdapter.serItemClickListener {
            val action = ActivitiesDirections.actionActivityMainFragmentToMyInfoFragment()
            findNavController().navigate(action)
        }

        App.INSTANCE.db.activityDao().getAll().observe(viewLifecycleOwner) { activitiesList ->
            val activitiesMap = mutableMapOf<String, MutableList<ListItem.MyCard>>()

            activitiesList.forEach {
                if (!activitiesMap.containsKey(it.finish.toDateSeparator())) {
                    activitiesMap[it.finish.toDateSeparator()] = mutableListOf()
                }

                activitiesMap[it.finish.toDateSeparator()]?.add(it.toMyCard())
            }

            val packedList = mutableListOf<ListItem>()

            activitiesMap.forEach { (dateSeparatorContent, myActivitiesList) ->
                packedList.add(ListItem.DateCard(dateSeparatorContent))
                myActivitiesList.forEach {
                    packedList.add(it)
                }
            }
            myListAdapter.submitList(packedList)
        }
    }
}