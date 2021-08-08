package com.example.fooddeliveryapp.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.auth0.android.jwt.JWT
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentSplashBinding
import com.example.fooddeliveryapp.model.local.SharedPrefManager
import com.example.fooddeliveryapp.ui.MainActivity
import java.time.Instant.now
import java.util.*

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
                val token = getToken()
                //val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZDY2YTlmNDgxYmQwMzA0MDY4ZTkxNiIsIm5hbWUiOiJUdW5haGFuIEF5ZMSxbm_En2x1IiwiaWF0IjoxNTAwMDAwMDAwLCJleHAiOjE2MDAwMDAwMDB9.xvxeo__9q4Q1WlOEhz2eIER4CL934zJ4jWKY98NrJ68"
                if (!isOnboardingSeen()) {
                    findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
                } else {
                    if (token.isNullOrEmpty()) {
                        findNavController().navigate(R.id.action_splashFragment_to_loginAndSignupFragment)
                    } else {
                        val jwt = JWT(token)

                        if (jwt.isExpired(0)) {
                            findNavController().navigate(R.id.action_splashFragment_to_loginAndSignupFragment)
                        } else {
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()

                        }
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

    private fun isOnboardingSeen(): Boolean {
        return SharedPrefManager(requireContext()).isOnboardingSeen()
    }
}