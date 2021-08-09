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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentProfileBinding
import com.example.fooddeliveryapp.model.entity.User
import com.example.fooddeliveryapp.ui.splash.SplashActivity
import com.example.fooddeliveryapp.utils.Resource
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
        addListeners()
        getProfile()
    }

    private fun getProfile() {
        viewModel.getUser().observe(viewLifecycleOwner, { response ->

            when (response.status) {
                Resource.Status.LOADING -> {
                    //_binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    setField(response.data?.user)
                    //_binding.progressBar.gone()
                }
                Resource.Status.ERROR -> {
                    //_binding.progressBar.gone()
                    Toast.makeText(context, "Operation Failed", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setField(user: User?) {
        _binding.nameTextView.text = user?.name
        _binding.mailTextView.text = user?.email
        _binding.addressTextView.text = user?.address

        val options = RequestOptions().placeholder(R.drawable.no_data)
        Glide.with(_binding.profilePhotoImageView.context)
            .applyDefaultRequestOptions(options)
            .load(user?.profileImage).into(_binding.profilePhotoImageView)
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