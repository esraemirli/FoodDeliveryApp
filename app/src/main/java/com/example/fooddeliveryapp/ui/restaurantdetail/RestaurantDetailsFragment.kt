package com.example.fooddeliveryapp.ui.restaurantdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.utils.RestaurantDetailViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class RestaurantDetailsFragment : Fragment() {
    lateinit var restaurantDetailTabLayout: TabLayout
    lateinit var restaurantDetailViewPager: ViewPager2
    lateinit var restaurantImageView: ImageView
    lateinit var addButton: AppCompatImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantDetailTabLayout = view.findViewById(R.id.restaurantDetailTabLayout)
        restaurantDetailViewPager = view.findViewById(R.id.restaurantDetailViewPager)
        restaurantImageView = view.findViewById(R.id.restaurantImageView)
        addButton = view.findViewById(R.id.addButton)

        /*TabLayoutMediator(restaurantDetailTabLayout, restaurantDetailViewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()*/
        initViewPager()
        initListener()
    }

    private fun initListener() {
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_restaurantDetailFragment_to_foodAddFragment)
        }
    }


    private fun initViewPager() {
        val adapter = RestaurantDetailViewPagerAdapter(requireActivity())
        restaurantDetailViewPager.adapter = adapter
        TabLayoutMediator(restaurantDetailTabLayout, restaurantDetailViewPager) { tab, position ->
            if (position == 0) {
                tab.text = "Details"
            }
            if (position == 1) {
                tab.text = "Menu"
            }
        }.attach()

        restaurantDetailTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //TODO Bug! When back button pressed image visibility is gone but in screen it is not gone
                if (tab?.position == 0) changeImageVisibility(true)
                if (tab?.position == 1) changeImageVisibility(false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.w("Unselected", "Tab unselected")

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.w("Reselected", "Tab reselected")

            }
        })
    }

    private fun changeImageVisibility(visible: Boolean) {
        if (visible) restaurantImageView.visibility =
            View.VISIBLE else restaurantImageView.visibility = View.GONE
    }


}