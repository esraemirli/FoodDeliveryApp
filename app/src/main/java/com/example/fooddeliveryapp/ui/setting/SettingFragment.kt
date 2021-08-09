package com.example.fooddeliveryapp.ui.setting


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentSettingsBinding
import com.example.fooddeliveryapp.model.entity.User

class SettingFragment : Fragment() {
    private lateinit var _binding: FragmentSettingsBinding
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        addListeners()
    }


    private fun initViews() {
        viewModel.getUser()?.observe(viewLifecycleOwner, { user ->
            _binding.nameEditText.setText(user.get().name)
            _binding.mailEditText.setText(user.get().email)
            _binding.phoneNumberEditText.setText(user.get().phone)
            _binding.addressEditText.setText(user.get().address)
            //TODO avatar / payment method
        })
    }

    private fun addListeners() {
        _binding.avatarConstraintLayout.setOnClickListener {
            //TODO radio group aç...

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
        _binding.updateButton.setOnClickListener {
            //TODO ...
            val name = _binding.nameEditText.text.toString()
            val mail = _binding.mailEditText.text.toString()
            val phone = _binding.phoneNumberEditText.text.toString()
            val address = _binding.addressEditText.text.toString()

            val user = User(name,mail,address,phone,"",1)
            viewModel.updateUser(user)?.observe(viewLifecycleOwner, { response ->
                if(response.get())
                    findNavController().navigate(R.id.action_settingFragment_to_profileFragment)
                else
                    Log.v("Setting", "Sorun oluştu")
            })
        }
    }

    private fun avatarChange() {
//        when (SharedPreferencesModule.getString("Avatar")) {
//            "1" -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_1_foreground)
//            }
//            "2" -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_2_foreground)
//            }
//            "3" -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_3_foreground)
//            }
//            "4" -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_4_foreground)
//            }
//            "5" -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_5_foreground)
//            }
//            "6" -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_6_foreground)
//            }
//            "7" -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_7_foreground)
//            }
//            "8" -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_8_foreground)
//            }
//            "9" -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_9_foreground)
//            }
//            else -> {
//                _binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_1_foreground)
//            }
//        }
    }

}

