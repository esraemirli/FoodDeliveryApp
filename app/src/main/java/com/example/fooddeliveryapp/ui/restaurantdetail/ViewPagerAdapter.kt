package com.example.fooddeliveryapp.ui.restaurantdetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val FRAGMENT_COUNT = 2

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RestaurantDetailsSectionFragment()
            1 -> MealsListFragment()
            else -> RestaurantDetailsSectionFragment()
        }
    }
}