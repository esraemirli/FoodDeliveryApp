package com.example.fooddeliveryapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.databinding.FragmentOnboardingBinding
import com.example.fooddeliveryapp.utils.DepthTransformation
import com.example.fooddeliveryapp.utils.ViewPagerAdapter

class OnboardingFragment: Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater,container,false)
        val view = binding.root
        init()
        return view
    }

    private fun init() {
        val adapter = ViewPagerAdapter(requireActivity())
        binding.onboardingViewPager.adapter = adapter
        binding.onboardingViewPager.setPageTransformer(DepthTransformation())
        binding.dotsIndicator.setViewPager2(binding.onboardingViewPager)
    }
}