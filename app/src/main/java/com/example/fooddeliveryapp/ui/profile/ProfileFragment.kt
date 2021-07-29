package com.example.fooddeliveryapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentProfileBinding
import com.example.fooddeliveryapp.utils.SharedPreferencesModule

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = FragmentProfileBinding.inflate(inflater, container, false)
//        return binding?.root
        return inflater.inflate(
            R.layout.fragment_profile,
            container,
            false
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SharedPreferencesModule.initSharedPreferences(requireActivity().baseContext)
        initViews()
        addListeners()

    }

    private fun initViews() {
        val name = SharedPreferencesModule.getString("Name")
        val mail = SharedPreferencesModule.getString("Mail")
        val phone = SharedPreferencesModule.getString("Phone")
        val address = SharedPreferencesModule.getString("Address")

        binding?.nameTextView?.text = name
        binding?.mailTextView?.text = mail
        binding?.phoneNumberTextView?.text = phone
        binding?.addressTextView?.text = address

    }

    private fun addListeners() {
        binding?.leftOkImageView?.setOnClickListener {
            //navigateToSetting()
        }
    }

}