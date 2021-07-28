package com.example.fooddeliveryapp.ui.restaurantlisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R

class RestaurantListingFragment : Fragment() {
    private lateinit var foodTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodTextView = view.findViewById(R.id.foodTextView)
        foodTextView.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_restaurantDetailFragment)
        })
    }
}