package com.example.fooddeliveryapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentProfileBinding
import com.example.fooddeliveryapp.ui.MainActivity
import com.example.fooddeliveryapp.ui.order.OrderFragmentViewModel
import com.example.fooddeliveryapp.ui.setting.SettingFragment
import com.example.fooddeliveryapp.ui.splash.SplashActivity
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

//import com.example.fooddeliveryapp.utils.SharedPreferencesModule

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //SharedPreferencesModule.initSharedPreferences(requireActivity().baseContext)
        initViews()
        addListeners()
        getProfile()
    }

    private fun getProfile() {
        viewModel.getLogin().observe(viewLifecycleOwner,{response ->

            when(response.status)
            {
                Resource.Status.LOADING ->
                {

                }
                Resource.Status.SUCCESS ->
                {
                    response.data?.let {
                        // TODO UI
                    var profileEmail = it.loginData.email
                    var profileName = it.loginData.name
                    var profileRole = it.loginData.role
                    }

                }

                Resource.Status.ERROR ->
                {
                    println("${response.message}")
                }
            }




        })


    }

    private fun initViews() {
//        val name = SharedPreferencesModule.getString("Name")
//        val mail = SharedPreferencesModule.getString("Mail")
//        val phone = SharedPreferencesModule.getString("Phone")
//        val address = SharedPreferencesModule.getString("Address")

//        binding?.nameTextView?.text = name
//        binding?.mailTextView?.text = mail
//        binding?.phoneNumberTextView?.text = phone
//        binding?.addressTextView?.text = address

        //avatarChange()
    }

    private fun addListeners() {
        binding?.profileChange?.setOnClickListener {
            navigateToSetting()
        }
        binding?.logOutCardView?.setOnClickListener{
            viewModel.logOut()
            val intent = Intent(context, SplashActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun navigateToSetting() {
//        val ft: FragmentTransaction = childFragmentManager.beginTransaction()
//        val fragment = SettingFragment()
//        ft.replace(R.id.nav_host_fragment_container, fragment, "SettingFragment")
//        ft.commit()

        findNavController().navigate(R.id.action_profileFragment_to_settingFragment)
    }

//    private fun avatarChange() {
//        when (SharedPreferencesModule.getString("Avatar")) {
//            "1" -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_1_foreground)
//            }
//            "2" -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_2_foreground)
//            }
//            "3" -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_3_foreground)
//            }
//            "4" -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_4_foreground)
//            }
//            "5" -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_5_foreground)
//            }
//            "6" -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_6_foreground)
//            }
//            "7" -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_7_foreground)
//            }
//            "8" -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_8_foreground)
//            }
//            "9" -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_9_foreground)
//            }
//            else -> {
//                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_1_foreground)
//            }
//        }
//    }

}