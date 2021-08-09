package com.example.fooddeliveryapp.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.databinding.FragmentMealsListBinding
import com.example.fooddeliveryapp.model.entity.meal.Meal
import com.example.fooddeliveryapp.ui.restaurantdetail.RestaurantDetailsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MealListFragment(
    private val mealList: ArrayList<Meal>
) : Fragment(), IMealOnClick {
    private var adapter: MealsListAdapter = MealsListAdapter()
    private lateinit var _binding: FragmentMealsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsListBinding.inflate(inflater, container, false)
        return _binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.mealsListRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter.setMealList(mealList)
        adapter.addListener(this)
        _binding.mealsListRecyclerView.adapter = adapter
    }


    override fun onClick(meal: Meal) {
        val action =
            RestaurantDetailsFragmentDirections.actionRestaurantDetailFragmentToMealDetailsFragment(
                meal.id
            )
        findNavController().navigate(action)
    }


}