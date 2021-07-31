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

        avatarChange()
    }

    private fun addListeners() {
     binding?.profileChange?.setOnClickListener {
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

    private fun avatarChange()
    {
        var avatar = SharedPreferencesModule.getString("Avatar")
        System.out.println("id ---- $avatar")
        when (avatar) {
            "1" -> {
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_1_foreground)
            }
            "2" -> {
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_2_foreground)
            }
            "3" -> {
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_3_foreground)
            }
            "4" ->{
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_4_foreground)
            }
            "5" ->{
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_5_foreground)
            }
            "6" -> {
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_6_foreground)
            }
            "7" -> {
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_7_foreground)
            }
            "8" ->{
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_8_foreground)
            }
            "9" -> {
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_9_foreground)
            }
            else -> {
                binding?.profilePhotoImageView?.setImageResource(R.mipmap.avatar_1_foreground)
            }
        }
    }


}