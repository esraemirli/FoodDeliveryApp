package com.example.fooddeliveryapp.ui.setting

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
    private var imageUrl: String? = null

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
        imageUrl = user?.profileImage

        val options = RequestOptions().placeholder(R.mipmap.no_data)
        Glide.with(_binding.avatarImageView.context)
            .applyDefaultRequestOptions(options)
            .load(imageUrl).into(_binding.avatarImageView)
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

        radioGroup.check(viewModel.getAvatarId(imageUrl!!))
        radioGroup.setOnCheckedChangeListener { _, _ ->
            imageUrl = viewModel.getImageUrl(radioGroup.checkedRadioButtonId)
        }
        builder.setPositiveButton(getString(R.string.save)) { _: DialogInterface, _: Int ->
            val options = RequestOptions().placeholder(R.mipmap.no_data)
            Glide.with(_binding.avatarImageView.context)
                .applyDefaultRequestOptions(options)
                .load(imageUrl).into(_binding.avatarImageView)
        }
        builder.show()
    }

    //TODO phone(String) ve paymentMethod(Int) servise eklenecek..
    private fun updateUser() {
        val name = _binding.nameEditText.text.toString()
        val mail = _binding.mailEditText.text.toString()
        val phone = _binding.phoneNumberEditText.text.toString()
        val address = _binding.addressEditText.text.toString()

        val user = UserRequest(mail, name, address, phone, imageUrl)
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
                    Toast.makeText(context, "Operation Failed", Toast.LENGTH_LONG).show()
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
                    //TODO geri dön
                }.show()
        }
    }

}

