package com.example.fooddeliveryapp.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentMealDetailsBinding
import com.example.fooddeliveryapp.databinding.FragmentMealsListBinding


class MealDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentMealDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return _binding.root
    }

}