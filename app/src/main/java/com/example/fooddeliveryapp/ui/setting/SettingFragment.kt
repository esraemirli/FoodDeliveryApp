package com.example.fooddeliveryapp.ui.setting

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentSettingsBinding
import com.example.fooddeliveryapp.model.entity.User
import com.example.fooddeliveryapp.model.entity.profile.UserRequest
import com.example.fooddeliveryapp.utils.Resource
import com.example.fooddeliveryapp.utils.gone
import com.example.fooddeliveryapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private lateinit var _binding: FragmentSettingsBinding
    private val viewModel: SettingViewModel by viewModels()
    private var image: Int = R.mipmap.no_data

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
        viewModel.getUser().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    setField(response.data?.user)
                    isSettingVisible(true)
                }
                Resource.Status.ERROR -> {
                    isSettingVisible(false)
                }
            }
        })
    }

    private fun setField(user: User?) {
        _binding.nameEditText.setText(user?.name)
        _binding.mailEditText.setText(user?.email)
        _binding.addressEditText.setText(user?.address)
        _binding.phoneNumberEditText.setText(user?.phone)
        image = user?.profileImage?.toInt() ?: R.mipmap.avatar_1_foreground
        _binding.avatarImageView.setImageResource(image)
    }

    private fun addListeners() {
        _binding.avatarConstraintLayout.setOnClickListener { changeAvatar(it) }
        _binding.updateButton.setOnClickListener { updateUser() }
        _binding.backImageView.setOnClickListener { findNavController().popBackStack() }
    }

    private fun changeAvatar(view: View) {
        val design: View = layoutInflater.inflate(R.layout.item_avatar_select, null)
        val radioGroup: RadioGroup = design.findViewById(R.id.avatarRadioGroup)
        val builder = AlertDialog.Builder(view.context)
        builder.setView(design)

        val selectedId = getRadioButtonId(image)
        radioGroup.check(selectedId)
        radioGroup.setOnCheckedChangeListener { _, _ ->
            image = getImageView(radioGroup.checkedRadioButtonId)
        }
        builder.setPositiveButton(getString(R.string.save)) { _: DialogInterface, _: Int ->
            _binding.avatarImageView.setImageResource(image)
        }
        builder.show()
    }

    private fun updateUser() {
        val name = _binding.nameEditText.text.toString()
        val mail = _binding.mailEditText.text.toString()
        val phone = _binding.phoneNumberEditText.text.toString()
        val address = _binding.addressEditText.text.toString()
        val paymentMethod = _binding.paymentRadioGroup.checkedRadioButtonId

        val user = UserRequest(mail, name, address, phone, image.toString(), paymentMethod)
        viewModel.updateUser(user).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    findNavController().navigate(R.id.action_settingFragment_to_profileFragment)
                    isSettingVisible(true)
                }
                Resource.Status.ERROR -> {
                    isSettingVisible(false)
                }
            }
        })
    }

    private fun isSettingVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.container.isVisible = isVisible
        if (isVisible.not()) {
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("There is a problem")
                .setPositiveButton("Cancel") { _, _ ->
                    findNavController().popBackStack()
                }.show()
        }
    }


    private fun getImageView(id: Int): Int {
        return when (id) {
            R.id.avatarRadioButton1 -> R.mipmap.avatar_1_foreground
            R.id.avatarRadioButton2 -> R.mipmap.avatar_2_foreground
            R.id.avatarRadioButton3 -> R.mipmap.avatar_3_foreground
            R.id.avatarRadioButton4 -> R.mipmap.avatar_4_foreground
            R.id.avatarRadioButton5 -> R.mipmap.avatar_5_foreground
            R.id.avatarRadioButton6 -> R.mipmap.avatar_6_foreground
            R.id.avatarRadioButton7 -> R.mipmap.avatar_7_foreground
            R.id.avatarRadioButton8 -> R.mipmap.avatar_8_foreground
            R.id.avatarRadioButton9 -> R.mipmap.avatar_9_foreground
            else -> R.mipmap.no_data
        }
    }

    private fun getRadioButtonId(image: Int): Int {
        return when (image) {
            R.mipmap.avatar_1_foreground -> R.id.avatarRadioButton1
            R.mipmap.avatar_2_foreground -> R.id.avatarRadioButton2
            R.mipmap.avatar_3_foreground -> R.id.avatarRadioButton3
            R.mipmap.avatar_4_foreground -> R.id.avatarRadioButton4
            R.mipmap.avatar_5_foreground -> R.id.avatarRadioButton5
            R.mipmap.avatar_6_foreground -> R.id.avatarRadioButton6
            R.mipmap.avatar_7_foreground -> R.id.avatarRadioButton7
            R.mipmap.avatar_8_foreground -> R.id.avatarRadioButton8
            R.mipmap.avatar_9_foreground -> R.id.avatarRadioButton9
            else -> 0
        }
    }


}

