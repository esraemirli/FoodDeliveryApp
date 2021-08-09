package com.example.fooddeliveryapp.ui.meal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentMealDetailsBinding
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {
    private val args: MealDetailsFragmentArgs by navArgs()
    private val viewModel: MealDetailsViewModel by viewModels()
    private lateinit var _binding: FragmentMealDetailsBinding
    private var adapter: MealIngredientsAdapter = MealIngredientsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingredientsDummy = ArrayList<String>()
        ingredientsDummy.add("ingredient1")
        ingredientsDummy.add("ingredient2")

        initViews()
        initListener()
    }

    private fun initViews() {
        viewModel.getMealDetails(args.mealId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.e("Loading", "loading")
                    // _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    //_binding.progressBar.gone()
                    val meal = it.data!!.data
                    val options = RequestOptions().placeholder(R.drawable.no_data)
                    Glide.with(_binding.mealImageView.context)
                        .applyDefaultRequestOptions(options)
                        .load(meal.image).into(_binding.mealImageView)
                    _binding.mealNameTextView.text = meal.name
                    _binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(context)
                    adapter.setIngredients(meal.ingredients)
                    _binding.ingredientsRecyclerView.adapter = adapter
                    _binding.priceTextView.text = meal.price

                    //_binding.homeTextView.text = "Count: ${it.data?.characters?.size}

                }
                Resource.Status.ERROR -> {
                    Log.e("Error", "error")
                    //_binding.progressBar.gone()
                }
            }
        })
    }

    private fun initListener() {
        _binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}