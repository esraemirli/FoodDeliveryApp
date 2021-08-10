package com.example.fooddeliveryapp.ui.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.databinding.FragmentOrderBinding
import com.example.fooddeliveryapp.ui.restaurantdetail.RestaurantDetailViewPagerAdapter
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {
    private var binding: FragmentOrderBinding? = null
    private val viewModel: OrderFragmentViewModel by viewModels()
    val adapter = OrderRecyclerViewAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getOrders()
    }

    private fun getOrders() {
        viewModel.getOrders().observe(viewLifecycleOwner, { response ->

            when (response.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    response.data?.orderList?.let {
                        binding?.orderRecyclerView?.layoutManager = LinearLayoutManager(context)
                        binding?.orderRecyclerView?.adapter = adapter
                        adapter.setOrderList(it)
                    }

                }

                Resource.Status.ERROR -> {
                    println("${response.message}")
                    Log.v("order",response.toString())
                }
            }
        })
    }
}