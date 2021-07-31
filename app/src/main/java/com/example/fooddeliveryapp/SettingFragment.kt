package com.example.fooddeliveryapp


import android.app.AlertDialog
import android.content.DialogInterface

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.fooddeliveryapp.databinding.FragmentSettingsBinding
import com.example.fooddeliveryapp.databinding.ItemAvatarSelectBinding
import com.example.fooddeliveryapp.utils.SharedPreferencesModule



class SettingFragment : Fragment() {
    private var binding : FragmentSettingsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSettingsBinding.inflate(inflater,container,false)

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    override fun onDestroy() {
        super.onDestroy()
        SharedPreferencesModule.unRegister()

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



        binding?.editNameEditText?.setText(name)
        binding?.editMailEditText?.setText(mail)
        binding?.editPhoneNumberEditText?.setText(phone)
        binding?.editAddressEditText?.setText(address)
        System.out.println("Name = ${name}")
        System.out.println("Mail = ${mail}")
        System.out.println("Phone = ${phone}")
        System.out.println("Address = ${address}")
        avatarChange()
    }

    private fun addListeners() {
        avatarChange()
        binding?.settingsUpdateButton?.setOnClickListener {

          var  name = binding?.editNameEditText?.text.toString()
          var  mail = binding?.editMailEditText?.text.toString()
          var  phone = binding?.editPhoneNumberEditText?.text.toString()
          var  address = binding?.editAddressEditText?.text.toString()

            SharedPreferencesModule.saveString("Name","${name}")
            SharedPreferencesModule.saveString("Mail","${mail}")
            SharedPreferencesModule.saveString("Phone","${phone}")
            SharedPreferencesModule.saveString("Address","${address}")



            navigateToProfile()


        }
        binding?.settingsAddImageView?.setOnClickListener {

            var design : View = layoutInflater.inflate(R.layout.item_avatar_select,null)
            val radioGroup: RadioGroup = design.findViewById(R.id.avatarRadioGroup)
            var avatarStatus : String = SharedPreferencesModule.getString("Avatar")
            val builder = AlertDialog.Builder(it.context)
            builder.setView(design)

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                System.out.println(" furkan ${group} $checkedId")
                when (radioGroup.checkedRadioButtonId) {
                    R.id.avatarRadioButton1  -> {
                        avatarStatus ="1"


                    }
                    R.id.avatarRadioButton2 -> {
                        avatarStatus ="2"


                    }
                    R.id.avatarRadioButton3 -> {
                        avatarStatus ="3"


                    }
                    R.id.avatarRadioButton4 -> {
                        avatarStatus ="4"


                    }
                    R.id.avatarRadioButton5 -> {
                        avatarStatus ="5"


                    }
                    R.id.avatarRadioButton6 -> {
                        avatarStatus ="6"


                    }
                    R.id.avatarRadioButton7 -> {
                        avatarStatus ="7"

                    }
                    R.id.avatarRadioButton8 -> {
                        avatarStatus ="8"

                    }
                    R.id.avatarRadioButton9 -> {
                        avatarStatus ="9"

                    }
                    else -> {
                        System.out.println("-1")
                    }

                }


            }


            builder.setPositiveButton("Save") { _: DialogInterface, _: Int ->

                SharedPreferencesModule.saveString("Avatar","$avatarStatus")
                avatarChange()
            }
            builder.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->

            }

            builder.show()

            }


        }

    private fun navigateToProfile()
    {
        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
        val fragment = ProfileFragment()
        ft.replace(R.id.fragment_container_view, fragment, "ProfileFragment")
        ft.commit()
    }
    private fun avatarChange()
    {
        var avatar = SharedPreferencesModule.getString("Avatar")
        System.out.println("id ---- $avatar")
        when (avatar) {
            "1" -> {
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_1_foreground)
            }
            "2" -> {
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_2_foreground)
            }
            "3" -> {
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_3_foreground)
            }
            "4" ->{
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_4_foreground)
            }
            "5" ->{
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_5_foreground)
            }
            "6" -> {
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_6_foreground)
            }
            "7" -> {
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_7_foreground)
            }
            "8" ->{
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_8_foreground)
            }
            "9" -> {
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_9_foreground)
            }
            else -> {
                binding?.settingsAvatarImageView?.setImageResource(R.mipmap.avatar_1_foreground)
            }
        }
    }

}

