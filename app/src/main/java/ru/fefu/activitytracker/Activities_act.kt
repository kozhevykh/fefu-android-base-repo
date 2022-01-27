package ru.fefu.activitytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.activitytracker.R

class Activities_act : Fragment() {
    private lateinit var fragmentAdapter: Adapter_act
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activities_act, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentAdapter = Adapter_act(this)
        viewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = fragmentAdapter
        tabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator (
            tabLayout,
            viewPager
        ) {
                tab, position ->
            tab.text = if (position == 0){
                "Мои"
            }
            else
                "Пользователей"
        }
            .attach()
    }

    companion object {
        fun newFragment() = Activities_act()
    }
} 