package com.example.fooddeliveryapp.ui.restaurantlisting

import android.app.ActionBar.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentRestaurantListingBinding
import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListingFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantListingBinding
    private val viewModel: RestaurantListingViewModel by viewModels()

    private lateinit var addRestaurant: AppCompatImageButton
    private lateinit var restaurantListRecyclerView: RecyclerView
    private lateinit var cuisineTypeLinearLayout: LinearLayout
    private var adapter = RestaurantListingAdapter()
    private var cuisineList: HashMap<String, MaterialButton> = hashMapOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restaurantListRecyclerView = view.findViewById(R.id.restaurantListRecyclerView)
        cuisineTypeLinearLayout = view.findViewById(R.id.cuisineTypeLinearLayout)
        addRestaurant = view.findViewById(R.id.addRestaurant)

        restaurantListRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        initViews()
        initObserver()
        addListener()
        addCuisineTypes()
        addCuisineTypesListener()
    }

    private fun initViews() {
        viewModel.getResponse()
    }

    private fun initObserver() {
//        viewModel.restaurantList.observe(viewLifecycleOwner, { restaurantList ->
//            adapter.setData(restaurantList)
//            restaurantListRecyclerView.adapter = adapter
//        })
    }

    private fun addListener() {
        addRestaurant.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_restaurantAddFragment)
        }
        adapter.addListener(object : RestaurantListingAdapterListener {
            override fun onRestaurantClickListener(restaurant: Restaurant) {
                //TODO bundle ile gönder..
                findNavController().navigate(R.id.action_homeFragment_to_restaurantDetailFragment)
            }
        })
    }

    private fun addCuisineTypesListener() {
        cuisineList.values.forEach { item ->
            item.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_grey))
            item.setOnClickListener {
                cuisineList.values.forEach { cuisine ->
                    cuisine.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_grey
                        )
                    )
                }
                item.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
            }
        }
    }

    private fun addCuisineTypes() {
        val list = listOf("Burger", "Pizza", "Tatlı", "İçecek", "Asya", "Uzak Doğu")
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(0, 0, 80, 0)

        list.forEach { item ->
            val button = MaterialButton(requireContext(), null, R.attr.materialButtonOutlinedStyle)
            button.text = item
            button.layoutParams = params
            button.isAllCaps = false
            cuisineTypeLinearLayout.addView(button)
            cuisineList[item] = button
        }
    }

}