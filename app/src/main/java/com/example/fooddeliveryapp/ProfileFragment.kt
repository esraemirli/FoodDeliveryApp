package com.example.fooddeliveryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.fooddeliveryapp.databinding.FragmentProfileBinding
import com.example.fooddeliveryapp.utils.SharedPreferencesModule

class ProfileFragment : Fragment(){
       private var binding : FragmentProfileBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProfileBinding.inflate(inflater,container,false)
        return binding?.root
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
        var name = SharedPreferencesModule.getString("Name")
        var mail = SharedPreferencesModule.getString("Mail")
        var phone = SharedPreferencesModule.getString("Phone")
        var address = SharedPreferencesModule.getString("Address")

        binding?.nameTextView?.text = name
        binding?.mailTextView?.text = mail
        binding?.phoneNumberTextView?.text = phone
        binding?.addressTextView?.text = address

    }

    private fun addListeners() {
     binding?.leftOkImageView?.setOnClickListener {
         navigateToSetting()
     }
    }


    private fun navigateToSetting()
    {
        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
        val fragment = SettingFragment()
        ft.replace(R.id.fragment_container_view, fragment, "SettingFragment")
        ft.commit()
    }
}