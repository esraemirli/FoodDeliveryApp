package com.example.fooddeliveryapp.ui.splash

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentSplashBinding
import com.example.fooddeliveryapp.model.local.SharedPrefManager
import com.example.fooddeliveryapp.ui.MainActivity

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

                if (!isOnboardingSeen()){
                    findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
                }else{
                    if(getToken() == ""){
                        findNavController().navigate(R.id.action_splashFragment_to_loginAndSignupFragment)
                    }
                    else{
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                }






            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }

        })
    }

    private fun getToken(): String? {
        return SharedPrefManager(requireContext()).getToken()
    }

    private fun isOnboardingSeen(): Boolean{
        return SharedPrefManager(requireContext()).isOnboardingSeen()
    }
}