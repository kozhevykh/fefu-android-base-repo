package ru.fefu.activitytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Nav_act: AppCompatActivity() {
    private var bottomNav: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen_act)

        bottomNav = findViewById(R.id.bottom_navigation)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add (
                    R.id.fragment_container,
                    Activities_act.newFragment(),
                    "activity"
                )
                commit()
            }
        }
        navSelect()
    }

    private fun navSelect() {
        bottomNav?.setOnItemSelectedListener { item->
            when (item.itemId) {
                R.id.menu_activity -> {
                    val profileFragment = supportFragmentManager.findFragmentByTag("profile")
                    val activityFragment = supportFragmentManager.findFragmentByTag("activity")
                    supportFragmentManager.beginTransaction().apply{
                            if(activityFragment != null) {
                                  attach(activityFragment)
                        }
                        else {
                            add(
                                R.id.fragment_container,
                                Activities_act.newFragment(),
                                "activity"
                            )
                        }
                        if (profileFragment != null)
                            detach(profileFragment)
                        commit()
                    }
                    true
                }
                R.id.menu_profile-> {
                    val activityFragment = supportFragmentManager.findFragmentByTag("activity")
                    val profileFragment = supportFragmentManager.findFragmentByTag("profile")
                    supportFragmentManager.beginTransaction().apply {
                        if(profileFragment != null) {
                            attach(profileFragment)
                        }
                        else {
                            add(
                                R.id.fragment_container,
                                Profile_act.newFragment(),
                                "profile"
                            )
                        }
                        if (activityFragment != null)
                            detach(activityFragment)
                        commit()
                    }
                    true
                }
                else -> false
            }
        }
    }
} 