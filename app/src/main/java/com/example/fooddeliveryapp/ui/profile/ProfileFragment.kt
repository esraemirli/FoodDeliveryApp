package com.example.fooddeliveryapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentProfileBinding
import com.example.fooddeliveryapp.model.entity.User
import com.example.fooddeliveryapp.ui.splash.SplashActivity
import com.example.fooddeliveryapp.utils.Resource
import com.example.fooddeliveryapp.utils.gone
import com.example.fooddeliveryapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val viewModel: ProfileFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.profileProgressBar.show()
        addListeners()
        getProfile()
    }

    private fun getProfile() {
        viewModel.getUser().observe(viewLifecycleOwner, { response ->

            when (response.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)

                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)
                    setField(response.data?.user)

                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                    Toast.makeText(context, "Operation Failed", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        if(isLoading)
        {
            _binding.profileProgressBar.show()
            _binding.myProfileTextView.gone()
            _binding.profileChange.gone()
            _binding.personalDetailsTextView.gone()
            _binding.ProfileCardView.gone()
            _binding.linearLayout2.gone()
        }
        else{
            _binding.profileProgressBar.gone()
            _binding.myProfileTextView.show()
            _binding.profileChange.show()
            _binding.personalDetailsTextView.show()
            _binding.ProfileCardView.show()
            _binding.linearLayout2.show()
        }
    }

    private fun setField(user: User?) {
        _binding.nameTextView.text = user?.name
        _binding.mailTextView.text = user?.email
        _binding.phoneNumberTextView.text = user?.phone
        _binding.addressTextView.text = user?.address
        val image = user?.profileImage?.toInt() ?: R.mipmap.avatar_1_foreground
        _binding.profilePhotoImageView.setImageResource(image)
    }

    private fun addListeners() {
        _binding.profileChange.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingFragment)
        }
        _binding.logOutCardView.setOnClickListener {
            viewModel.logOut()
            val intent = Intent(context, SplashActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }



}