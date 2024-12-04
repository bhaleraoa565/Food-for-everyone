package com.example.foodforeveryone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class DashboardActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var card:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tabLayout = findViewById(R.id.tab_dashboard_layout)
        viewPager = findViewById(R.id.dashboard_view_pager)
        card = findViewById(R.id.cart)
        card.setOnClickListener {
            val intent = Intent(this,CartActivity::class.java)
            startActivity(intent)
        }


        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = FoodItemAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // No action needed here
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // No action needed here
            }
        })
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_UNLABELED

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    true
                }
                R.id.nav_favorites -> {
                    val intent = Intent(this, FavouriteActivity::class.java)
                    startActivity(intent)
                    true
                    true
                }
                R.id.nav_profile -> {
                    // Load Profile Fragment or Activity
                   // loadFragment(ProfileFragment())
                    true
                }
                R.id.nav_history -> {
                    // Load History Fragment or Activity
                   // loadFragment(HistoryFragment())
                    true
                }
                else -> false
            }
        }

    }
}