package com.example.fooddeliveryapp.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.Ingredient
import com.example.fooddeliveryapp.model.Meal
import com.example.fooddeliveryapp.utils.IMealOnClick
import com.example.fooddeliveryapp.utils.adapter.MealsListAdapter


class MealListFragment : Fragment(), IMealOnClick {
    private lateinit var mealsListRecyclerView: RecyclerView
    private var adapter: MealsListAdapter = MealsListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealsListRecyclerView = view.findViewById(R.id.mealsListRecyclerView)
        mealsListRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter.addListener(this)
        mealsListRecyclerView.adapter = adapter
        setData()
    }

    private fun setData() {
        val data = ArrayList<Meal>()
        for (i in 0..10) {
//            data.add(
//                Meal(
//                    "https://via.placeholder.com/150",
//                    "Title - $i",
//                    listOf(
//                        Ingredient("Tomato", true),
//                        Ingredient("Pepper",true),
//                        Ingredient("Onion", true),
//                    )
//                )
//            )
        }
        adapter.setMealList(data)
    }

    override fun onPause() {
        super.onPause()
        adapter.removeListeners()
    }

    override fun onClick(meal: Meal) {
        findNavController().navigate(R.id.action_restaurantDetailFragment_to_mealDetailsFragment)
    }


}