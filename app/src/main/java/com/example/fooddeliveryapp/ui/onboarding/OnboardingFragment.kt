package com.example.fooddeliveryapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentOnboardingBinding
import com.example.fooddeliveryapp.utils.DepthTransformation
import com.example.fooddeliveryapp.utils.adapter.ViewPagerAdapter

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = ViewPagerAdapter(requireActivity())
        binding.onboardingViewPager.adapter = adapter
        binding.onboardingViewPager.setPageTransformer(DepthTransformation())
        binding.dotsIndicator.setViewPager2(binding.onboardingViewPager)

        binding.onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.prevButton.visibility = View.GONE
                    binding.nextButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem + 1
                    }
                } else if (position == 2) {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = resources.getText(R.string.finish)
                    binding.nextButton.setOnClickListener {
                        findNavController(requireParentFragment()).navigate(R.id.action_onboardingFragment_to_loginAndSignupFragment)
                    }
                } else {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = resources.getText(R.string.next)
                    binding.nextButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem + 1
                    }
                    binding.prevButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem - 1
                    }
                }
            }
        })

    }


}