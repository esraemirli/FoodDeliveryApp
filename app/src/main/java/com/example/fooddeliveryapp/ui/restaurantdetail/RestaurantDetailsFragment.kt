package com.example.fooddeliveryapp.ui.restaurantdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentRestaurantDetailBinding
import com.example.fooddeliveryapp.utils.Resource
import com.example.fooddeliveryapp.utils.gone
import com.example.fooddeliveryapp.utils.show
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailsFragment : Fragment() {
    private val args: RestaurantDetailsFragmentArgs by navArgs()
    private lateinit var _binding: FragmentRestaurantDetailBinding
    private val viewModel: RestaurantDetailsViewModel by viewModels()
    private var selectedTab: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListener()
    }

    private fun initViews() {
        viewModel.getRestaurantDetail(args.restaurantId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    val restaurant = it.data!!.data
                    _binding.progressBar.gone()
                    val options = RequestOptions().placeholder(R.drawable.no_data)
                    Glide.with(_binding.restaurantImageView.context)
                        .applyDefaultRequestOptions(options)
                        .load(restaurant.image).into(_binding.restaurantImageView)


                    val adapter = RestaurantDetailViewPagerAdapter(requireActivity(), restaurant)

                    initViewPager(adapter)

                    //_binding.homeTextView.text = "Count: ${it.data?.characters?.size}

                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                    //_binding.homeTextView.setTextColor(resources.getColor(R.color.red))
                    //_binding.homeTextView.text = "Error: ${it?.message}"
                }
            }
        })
    }

    private fun initListener() {
        _binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        _binding.addButton.setOnClickListener {
            val action =
                RestaurantDetailsFragmentDirections.actionRestaurantDetailFragmentToFoodAddFragment(
                    args.restaurantId
                )
            findNavController().navigate(action)
        }
        _binding.restaurantDetailTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab?.position == 0) {
                    changeImageVisibility(true)
                    selectedTab = 0
                }
                if (tab?.position == 1) {
                    changeImageVisibility(false)
                    selectedTab = 1
                }
                Log.v(
                    "SelectedListener",
                    _binding.restaurantDetailTabLayout.selectedTabPosition.toString()
                )
                Log.e("Visibility", _binding.restaurantImageView.visibility.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.w("Unselected", "Tab unselected")

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.w("Reselected", "Tab reselected")

            }
        })
    }

    override fun onResume() {
        super.onResume()
        Log.v("Resume", _binding.restaurantDetailTabLayout.selectedTabPosition.toString())

        if (selectedTab == 0) changeImageVisibility(true)
        else if (selectedTab == 1) changeImageVisibility(false)
        else changeImageVisibility(true)
    }


    private fun initViewPager(adapter: RestaurantDetailViewPagerAdapter) {
        _binding.restaurantDetailViewPager.adapter = adapter
        TabLayoutMediator(
            _binding.restaurantDetailTabLayout,
            _binding.restaurantDetailViewPager
        ) { tab, position ->
            if (position == 0) {
                tab.text = "Details"

            }
            if (position == 1) {
                tab.text = "Menu"

            }
        }.attach()

    }

    private fun changeImageVisibility(visible: Boolean) {
        if (visible) _binding.restaurantImageView.visibility =
            View.VISIBLE else _binding.restaurantImageView.visibility = View.GONE
    }


}