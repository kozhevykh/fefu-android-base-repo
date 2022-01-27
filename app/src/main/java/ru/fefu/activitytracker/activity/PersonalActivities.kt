package ru.fefu.activitytracker.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.PersonalActivitiesBinding
import ru.fefu.activitytracker.activity.adapters.MyListAdapter
import ru.fefu.activitytracker.activity.cards.ICard
import ru.fefu.activitytracker.activity.cards.PersonalCardsRepository
import screens.activity.PersonalCardInfo

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
            val fragmentManager = parentFragment?.parentFragmentManager
            val currentFragment = fragmentManager?.findFragmentByTag("MyCardInfo")
            val activityFragment = fragmentManager?.findFragmentByTag("activity")
            fragmentManager?.beginTransaction()?.apply {
                if (currentFragment != null) {
                    detach(currentFragment)
                }
                add(
                    R.id.fragment_container,
                    PersonalCardInfo(),
                    "MyCardInfo"
                )
                if (activityFragment != null) {
                    detach(activityFragment)
                }
                addToBackStack("MyCardInfo")
                commit()
            }
        }
    }
} 