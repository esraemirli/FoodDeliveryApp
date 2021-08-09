package com.example.fooddeliveryapp.ui.meal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentMealDetailsBinding
import com.example.fooddeliveryapp.databinding.FragmentMealsListBinding
import com.example.fooddeliveryapp.ui.restaurantdetail.RestaurantDetailViewPagerAdapter
import com.example.fooddeliveryapp.ui.restaurantdetail.RestaurantDetailsFragmentArgs
import com.example.fooddeliveryapp.utils.Resource
import com.example.fooddeliveryapp.utils.gone
import com.example.fooddeliveryapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {
    private val args: MealDetailsFragmentArgs by navArgs()
    private val viewModel: MealDetailsViewModel by viewModels()
    private lateinit var _binding: FragmentMealDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        Log.e("Meal Id", args.mealId)
        viewModel.getMealDetails(args.mealId).observe(viewLifecycleOwner,{
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.e("Loading","loading")
                   // _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    //_binding.progressBar.gone()
                    val meal = it.data!!.data
                    val options = RequestOptions().placeholder(R.drawable.no_data)
                    Glide.with(_binding.mealImageView.context)
                        .applyDefaultRequestOptions(options)
                        .load(meal.image).into(_binding.mealImageView)
                    _binding.mealNameTextView.text= meal.name

                    //_binding.homeTextView.text = "Count: ${it.data?.characters?.size}

                }
                Resource.Status.ERROR -> {
                    Log.e("Error","error")
                    //_binding.progressBar.gone()
                }
            }
        })
    }

}