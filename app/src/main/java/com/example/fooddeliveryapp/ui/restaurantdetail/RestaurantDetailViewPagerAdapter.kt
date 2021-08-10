package com.example.fooddeliveryapp.ui.restaurantdetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant
import com.example.fooddeliveryapp.ui.meal.MealListFragment

private const val FRAGMENT_COUNT = 2

class RestaurantDetailViewPagerAdapter(fragment: FragmentActivity, val restaurant: Restaurant) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RestaurantDetailsSectionFragment(restaurant)
            1 -> MealListFragment(restaurant)
            else -> RestaurantDetailsSectionFragment(restaurant)
        }
    }
}