package com.example.fooddeliveryapp.ui.restaurantlisting

import android.app.ActionBar.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentRestaurantListingBinding
import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant
import com.example.fooddeliveryapp.utils.Resource
import com.example.fooddeliveryapp.utils.gone
import com.example.fooddeliveryapp.utils.show
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RestaurantListingFragment : Fragment() {
    private lateinit var _binding: FragmentRestaurantListingBinding
    private val viewModel: RestaurantListingViewModel by viewModels()

    private var adapter = RestaurantListingAdapter()
    private var cuisineList: HashMap<String, MaterialButton> = hashMapOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantListingBinding.inflate(inflater, container, false)
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
                Resource.Status.LOADING -> _binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    response.data?.restaurantList?.let { restaurantList ->
                        showRestaurantList(restaurantList)
                        if(_binding.cuisineTypeLinearLayout.isEmpty())
                            setCuisineList(restaurantList.map { it.cuisine }.toMutableList())
                    }
                }
                Resource.Status.ERROR -> showResponseError()
            }
        })
    }

    private fun setRestaurants(restaurantList: List<Restaurant>) {
        adapter.setData(restaurantList)
        _binding.restaurantListRecyclerView.adapter = adapter
    }

    private fun setCuisineList(list: MutableList<String>) {
        _binding.cuisineTypeLinearLayout.removeAllViews()
        list.add(0, getString(R.string.all_restaurants))
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(0, 0, 80, 0)

        list.forEachIndexed { index, item ->
            val button = MaterialButton(requireContext(), null, R.attr.materialButtonOutlinedStyle)
            button.text = item
            button.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    if (index == 0)
                        R.color.orange
                    else
                        R.color.light_grey
                )
            )
            button.layoutParams = params
            button.isAllCaps = false
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
                val action =
                    RestaurantListingFragmentDirections.actionHomeFragmentToRestaurantDetailFragment(
                        restaurant.id
                    )
                findNavController().navigate(action)
            }
        })
    }

    private fun addCuisineTypesListener() {
        cuisineList.forEach { cuisine ->
            cuisine.value.setOnClickListener {
                //clear other text color
                cuisineList.values.forEach { cuisine ->
                    cuisine.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_grey
                        )
                    )
                }
                //make orange selected text
                cuisine.value.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
                if(cuisine.key == getString(R.string.all_restaurants))
                    getRestaurants()
                else
                    sendCuisineRequest(cuisine.key)
            }
        }
    }

    private fun sendCuisineRequest(cuisineName: String) {
        viewModel.getRestaurantByCuisine(cuisineName).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    response.data?.restaurantList?.let { restaurantList ->
                        showRestaurantList(restaurantList)
                    }
                }
                Resource.Status.ERROR -> showResponseError()
            }
        })
    }

    private fun showRestaurantList(restaurantList: List<Restaurant>) {
        if(restaurantList.isEmpty())
            showResponseError()
        _binding.responseErrorLinearLayout.gone()
        _binding.restaurantListRecyclerView.isVisible = true
        setRestaurants(restaurantList)
    }

    private fun showResponseError() {
        _binding.progressBar.gone()
        _binding.restaurantListRecyclerView.gone()
        _binding.responseErrorLinearLayout.isVisible = true
    }

}