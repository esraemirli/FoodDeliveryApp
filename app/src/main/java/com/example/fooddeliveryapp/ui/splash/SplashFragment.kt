package com.example.fooddeliveryapp.ui.splash

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        init()
        return view
    }

    private fun init() {
        binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                if(isOnboardingSeen()){
                    findNavController().navigate(R.id.action_splashFragment_to_loginAndSignupFragment)
                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
                }

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }

        })
    }

    private fun isOnboardingSeen(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("onboarding",false)
    }
}