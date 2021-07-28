package com.example.fooddeliveryapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.databinding.FragmentLoginAndSignupBinding
import com.example.fooddeliveryapp.utils.CustomViewPager
import com.google.android.material.tabs.TabLayout

class LoginAndSignupFragment:Fragment() {
    private lateinit var binding: FragmentLoginAndSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginAndSignupBinding.inflate(inflater,container,false)
        val view = binding.root
        init()
        return view
    }

    private fun init() {
        val adapter = CustomViewPager(requireFragmentManager())
        adapter.addFragment(LoginFragment(),"Login")
        adapter.addFragment(SignupFragment(),"Sign-up")
        binding.loginAndSignupViewPager.adapter = adapter
        binding.loginAndSignupTabLayout.setupWithViewPager(binding.loginAndSignupViewPager)
    }
}