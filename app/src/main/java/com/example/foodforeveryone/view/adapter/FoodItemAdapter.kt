package com.example.foodforeveryone.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.foodforeveryone.view.fragment.FoodsFragment

class FoodItemAdapter(
    fm: FragmentManager,
    private val tabCount: Int
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FoodsFragment()
            1 -> FoodsFragment()
            2 -> FoodsFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}
