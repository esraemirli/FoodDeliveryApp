package com.example.fooddeliveryapp


import android.app.AlertDialog
import android.content.DialogInterface

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.fooddeliveryapp.databinding.FragmentSettingsBinding
import com.example.fooddeliveryapp.databinding.ItemAvatarSelectBinding
import com.example.fooddeliveryapp.utils.SharedPreferencesModule



class SettingFragment : Fragment() {
    private var binding : FragmentSettingsBinding? = null
    private var binding2 : ItemAvatarSelectBinding? = null
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

        initviews()
        addListeners()



    }


    private fun initviews() {
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
    }

    private fun addListeners() {
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
        binding?.settingsImageView?.setOnClickListener {
            var design : View = layoutInflater.inflate(R.layout.item_avatar_select,null)
            val radioGroup: RadioGroup = design.findViewById(R.id.avatarRadioGroup)

                when (radioGroup.checkedRadioButtonId) {
                    R.id.avatarRadioButton1 -> {
                        System.out.println("1")
                    }
                    R.id.avatarRadioButton2 -> {
                        System.out.println("2")
                    }
                    R.id.avatarRadioButton3 -> {
                        System.out.println("3")
                    }
                    R.id.avatarRadioButton4 -> {
                        System.out.println("4")
                    }
                    R.id.avatarRadioButton5 -> {
                        System.out.println("5")
                    }
                    R.id.avatarRadioButton6 -> {
                        System.out.println("6")
                    }
                    R.id.avatarRadioButton7 -> {
                        System.out.println("7")
                    }
                    R.id.avatarRadioButton8 -> {
                        System.out.println("8")
                    }
                    R.id.avatarRadioButton9 -> {
                        System.out.println("9")
                    }
                    else -> {
                        System.out.println("0")
                    }
                }



            val builder = AlertDialog.Builder(it.context)
            builder.setView(R.layout.item_avatar_select)

            builder.setPositiveButton("Save") { dialogInterface: DialogInterface, i: Int ->
                System.out.println("basildi")
                var design : View = layoutInflater.inflate(R.layout.item_avatar_select,null)
                val radioGroup: RadioGroup = design.findViewById(R.id.avatarRadioGroup)
                radioGroup.setOnCheckedChangeListener { group, checkedId ->
                    System.out.println(" furkan ${group} $checkedId")
                }

                when (radioGroup.checkedRadioButtonId) {
                    2131362294 -> {
                        System.out.println("1")
                    }
                    R.id.avatarRadioButton2 -> {
                        System.out.println("2")
                    }
                    R.id.avatarRadioButton3 -> {
                        System.out.println("3")
                    }
                    R.id.avatarRadioButton4 -> {
                        System.out.println("4")
                    }
                    R.id.avatarRadioButton5 -> {
                        System.out.println("5")
                    }
                    R.id.avatarRadioButton6 -> {
                        System.out.println("6")
                    }
                    R.id.avatarRadioButton7 -> {
                        System.out.println("7")
                    }
                    R.id.avatarRadioButton8 -> {
                        System.out.println("8")
                    }
                    R.id.avatarRadioButton9 -> {
                        System.out.println("9")
                    }
                    else -> {
                        System.out.println("0")
                    }
                }
            }
            builder.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
                System.out.println("ddddds")
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

}
