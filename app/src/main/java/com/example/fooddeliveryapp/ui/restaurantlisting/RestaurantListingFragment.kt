package com.example.fooddeliveryapp.ui.restaurantlisting

import android.app.ActionBar.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentRestaurantListingBinding
import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant
import com.example.fooddeliveryapp.utils.Resource
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RestaurantListingFragment : Fragment() {
    private lateinit var _binding : FragmentRestaurantListingBinding
    private val viewModel: RestaurantListingViewModel by viewModels()

    private var adapter = RestaurantListingAdapter()
    private var cuisineList: HashMap<String, MaterialButton> = hashMapOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentRestaurantListingBinding.inflate(inflater, container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding.restaurantListRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        getRestaurants()
        addListener()
    }

    private fun getRestaurants() {
        viewModel.getRestaurants().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    //TODO progress bar
                    Log.v("RestaurantListing Load", response.message.toString())
                }
                Resource.Status.SUCCESS -> {
                    response.data?.restaurantList?.let { restaurantList ->
                        setRestaurants(restaurantList)
                        setCuisineList(restaurantList.map { it.cuisine }.toList())
                    }
                }
                else -> Log.v("RestaurantListing Fail", response.message.toString())
            }
        })
    }

    private fun setRestaurants(restaurantList: List<Restaurant>) {
        adapter.setData(restaurantList)
        _binding.restaurantListRecyclerView.adapter = adapter
    }

    private fun setCuisineList(list: List<String>) {
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(0, 0, 80, 0)

        list.forEachIndexed { index, item ->
            val button = MaterialButton(requireContext(), null, R.attr.materialButtonOutlinedStyle)
            button.text = item
            button.layoutParams = params
            button.isAllCaps = false
            if(index == 0)
               button.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
            _binding.cuisineTypeLinearLayout.addView(button)
            cuisineList[item] = button
        }
        addCuisineTypesListener()
    }


    private fun addListener() {
        _binding.addRestaurant.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_restaurantAddFragment)
        }
        adapter.addListener(object : IRestaurantOnClick {
            override fun onClick(restaurant: Restaurant) {
                val action = RestaurantListingFragmentDirections.actionHomeFragmentToRestaurantDetailFragment(restaurant.id)
                findNavController().navigate(action)
            }
        })
    }

    private fun addCuisineTypesListener() {
        cuisineList.forEach { item ->
            item.value.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_grey))
            item.value.setOnClickListener {
                cuisineList.values.forEach { cuisine ->
                    cuisine.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_grey
                        )
                    )
                }
                item.value.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
                sendCuisineRequest(item.key)
            }
        }
    }

    private fun sendCuisineRequest(cuisineName: String) {
        viewModel.getRestaurantByCuisine(cuisineName).observe(viewLifecycleOwner,{ response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    //TODO progress bar
                    Log.v("RestaurantListing Load", response.message.toString())
                }
                Resource.Status.SUCCESS -> {
                    response.data?.restaurantList?.let { restaurantList ->
                        setRestaurants(restaurantList)
                    }
                }
                else -> Log.v("RestaurantListing Fail", response.message.toString())
            }
        })
    }

}