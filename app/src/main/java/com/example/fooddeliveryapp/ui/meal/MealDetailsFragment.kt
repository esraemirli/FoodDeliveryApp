package com.example.fooddeliveryapp.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentMealDetailsBinding
import com.example.fooddeliveryapp.databinding.FragmentMealsListBinding
import com.example.fooddeliveryapp.ui.restaurantdetail.RestaurantDetailsFragmentArgs


class MealDetailsFragment : Fragment() {
    private val args: MealDetailsFragmentArgs by navArgs()

    private lateinit var _binding: FragmentMealDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return _binding.root
    }

}