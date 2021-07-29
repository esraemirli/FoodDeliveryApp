package com.example.fooddeliveryapp.ui.restaurantlisting

import android.app.ActionBar.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.Restaurant
import com.example.fooddeliveryapp.utils.RestaurantListingAdapter
import com.example.fooddeliveryapp.utils.RestaurantListingAdapterListener
import com.google.android.material.button.MaterialButton

class RestaurantListingFragment : Fragment() {

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
        restaurantListRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        initViews()
        addListener()
        addCuisineTypes()
        addCuisineTypesListener()
    }

    private fun addListener() {
        adapter.addListener(object : RestaurantListingAdapterListener {

            override fun onRestaurantClickListener(restaurant: Restaurant) {
                Toast.makeText(context, "${restaurant.id} - ${restaurant.name}", Toast.LENGTH_SHORT)
                    .show()
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

    private fun initViews() {
        val list = listOf(
            Restaurant(1, "Ali Usta'nın Yeri", "Kadıköy"),
            Restaurant(2, "KFC", "Tuzla"),
            Restaurant(3, "Tavuk Dünyası", "Kadıköy"),
            Restaurant(4, "Deli Kasap", "Maltepe")
        )
        adapter.setData(list)
        restaurantListRecyclerView.adapter = adapter
    }
}