package com.example.fooddeliveryapp.ui.restaurantdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
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
        viewModel.getRestaurantDetail(args.restaurantId).observe(viewLifecycleOwner,{
                Log.v("Restaurant", it.data.toString())
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    val adapter = RestaurantDetailViewPagerAdapter(requireActivity(),it.data!!.data)

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
        _binding.backButton.setOnClickListener{

        }
        _binding.addButton.setOnClickListener {
            val action = RestaurantDetailsFragmentDirections.actionRestaurantDetailFragmentToFoodAddFragment(args.restaurantId)
            findNavController().navigate(action)
        }
    }


    private fun initViewPager(adapter: RestaurantDetailViewPagerAdapter) {
        _binding.restaurantDetailViewPager.adapter = adapter
        TabLayoutMediator(_binding.restaurantDetailTabLayout, _binding.restaurantDetailViewPager) { tab, position ->
            if (position == 0) {
                tab.text = "Details"
            }
            if (position == 1) {
                tab.text = "Menu"
            }
        }.attach()

        _binding.restaurantDetailTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //TODO Bug! When back button pressed image visibility is gone but in screen it is not gone
                if (tab?.position == 0) changeImageVisibility(true)
                if (tab?.position == 1) changeImageVisibility(false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.w("Unselected", "Tab unselected")

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.w("Reselected", "Tab reselected")

            }
        })
    }

    private fun changeImageVisibility(visible: Boolean) {
        if (visible) _binding.restaurantImageView.visibility =
            View.VISIBLE else _binding.restaurantImageView.visibility = View.GONE
    }


}