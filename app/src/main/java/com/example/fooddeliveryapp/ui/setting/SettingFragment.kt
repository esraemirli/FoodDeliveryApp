package com.example.fooddeliveryapp.ui.setting


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentSettingsBinding
import com.example.fooddeliveryapp.ui.profile.ProfileFragment
//import com.example.fooddeliveryapp.utils.SharedPreferencesModule


class SettingFragment : Fragment() {
    private var binding: FragmentSettingsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        //SharedPreferencesModule.unRegister()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //SharedPreferencesModule.initSharedPreferences(requireActivity().baseContext)

        initViews()
        addListeners()
    }


    private fun initViews() {
//        val name = SharedPreferencesModule.getString("Name")
//        val mail = SharedPreferencesModule.getString("Mail")
//        val phone = SharedPreferencesModule.getString("Phone")
//        val address = SharedPreferencesModule.getString("Address")
//
//
//        binding?.editNameEditText?.setText(name)
//        binding?.editMailEditText?.setText(mail)
//        binding?.editPhoneNumberEditText?.setText(phone)
//        binding?.editAddressEditText?.setText(address)
//
//        println("Name = $name")
//        println("Mail = $mail")
//        println("Phone = $phone")
//        println("Address = $address")
//        avatarChange()
    }

    private fun addListeners() {
        //avatarChange()
        binding?.settingsUpdateButton?.setOnClickListener {

            val name = binding?.editNameEditText?.text.toString()
            val mail = binding?.editMailEditText?.text.toString()
            val phone = binding?.editPhoneNumberEditText?.text.toString()
            val address = binding?.editAddressEditText?.text.toString()

//            SharedPreferencesModule.saveString("Name", name)
//            SharedPreferencesModule.saveString("Mail", mail)
//            SharedPreferencesModule.saveString("Phone", phone)
//            SharedPreferencesModule.saveString("Address", address)

            navigateToProfile()
        }
        binding?.settingsAddImageView?.setOnClickListener {

            val design: View = layoutInflater.inflate(R.layout.item_avatar_select, null)
            val radioGroup: RadioGroup = design.findViewById(R.id.avatarRadioGroup)
            //var avatarStatus: String = SharedPreferencesModule.getString("Avatar")
            val builder = AlertDialog.Builder(it.context)
            builder.setView(design)

//            radioGroup.setOnCheckedChangeListener { _, _ ->
//                when (radioGroup.checkedRadioButtonId) {
//                    R.id.avatarRadioButton1 -> {
//                        avatarStatus = "1"
//                    }
//                    R.id.avatarRadioButton2 -> {
//                        avatarStatus = "2"
//                    }
//                    R.id.avatarRadioButton3 -> {
//                        avatarStatus = "3"
//                    }
//                    R.id.avatarRadioButton4 -> {
//                        avatarStatus = "4"
//                    }
//                    R.id.avatarRadioButton5 -> {
//                        avatarStatus = "5"
//                    }
//                    R.id.avatarRadioButton6 -> {
//                        avatarStatus = "6"
//                    }
//                    R.id.avatarRadioButton7 -> {
//                        avatarStatus = "7"
//                    }
//                    R.id.avatarRadioButton8 -> {
//                        avatarStatus = "8"
//                    }
//                    R.id.avatarRadioButton9 -> {
//                        avatarStatus = "9"
//                    }
//                    else -> {
//                        println("-1")
//                    }
//
//                }
//            }

            builder.setPositiveButton("Save") { _: DialogInterface, _: Int ->

//                SharedPreferencesModule.saveString("Avatar", avatarStatus)
//                avatarChange()
            }
            builder.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
            }

            builder.show()

        }


    }

    private fun navigateToProfile() {
//        val ft: FragmentTransaction = childFragmentManager.beginTransaction()
//        val fragment = ProfileFragment()
//        ft.replace(R.id.nav_host_fragment_container, fragment, "ProfileFragment")
//        ft.commit()
        findNavController().navigate(R.id.action_settingFragment_to_profileFragment)
    }

//    private fun avatarChange() {
//        when (SharedPreferencesModule.getString("Avatar")) {
//            "1" -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_1_foreground)
//            }
//            "2" -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_2_foreground)
//            }
//            "3" -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_3_foreground)
//            }
//            "4" -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_4_foreground)
//            }
//            "5" -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_5_foreground)
//            }
//            "6" -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_6_foreground)
//            }
//            "7" -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_7_foreground)
//            }
//            "8" -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_8_foreground)
//            }
//            "9" -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_9_foreground)
//            }
//            else -> {
//                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_1_foreground)
//            }
//        }
//    }

}

