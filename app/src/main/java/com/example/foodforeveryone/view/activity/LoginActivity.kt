package com.example.foodforeveryone.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.foodforeveryone.view.adapter.FragmentAdapter
import com.example.foodforeveryone.R
import com.example.foodforeveryone.viewmodel.LoginViewModel
import com.google.android.material.tabs.TabLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = FragmentAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        // Observe ViewModel for tab selection updates
        loginViewModel.selectedTabIndex.observe(this, Observer { index ->
            viewPager.currentItem = index
        })

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                loginViewModel.updateSelectedTab(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // No action needed here
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // No action needed here
            }
        })
    }
}