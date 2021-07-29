package com.example.fooddeliveryapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fooddeliveryapp.ui.meal.MealsListFragment
import com.example.fooddeliveryapp.ui.restaurantdetail.RestaurantDetailsSectionFragment

private const val FRAGMENT_COUNT = 2

class RestaurantDetailViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RestaurantDetailsSectionFragment()
            1 -> MealsListFragment()
            else -> RestaurantDetailsSectionFragment()
        }
    }
}