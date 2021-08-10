package com.example.fooddeliveryapp.ui.restaurantadd

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentRestaurantAddBinding
import com.example.fooddeliveryapp.utils.Resource
import com.example.fooddeliveryapp.utils.afterTextChanged
import com.example.fooddeliveryapp.utils.gone
import com.example.fooddeliveryapp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RestaurantAddFragment : Fragment() {

    private lateinit var _binding: FragmentRestaurantAddBinding
    private val viewModel: RestaurantAddViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantAddBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayHomeAsUpEnabled(true)
        }
        Glide.with(requireContext())
            .load("https://firebasestorage.googleapis.com/v0/b/fooddeliveryapp-fe5bf.appspot.com/o/images%2FRestaurant2.jpg?alt=media&token=3d28246b-52b4-4030-80bf-84799080fdf7")
            .into(_binding.addRestaurantImageView)
        addListeners()
        initializeCitySpinner()
        initializeCuisineSpinner()
        initializePaymentMethodsSpinner()
    }

    private fun addListeners() {
        _binding.restaurantNameEditText.editText?.afterTextChanged(_binding.restaurantNameEditText)
        _binding.restaurantPhoneEditText.editText?.afterTextChanged(_binding.restaurantPhoneEditText)
        _binding.restaurantWebsiteEditText.editText?.afterTextChanged(_binding.restaurantWebsiteEditText)
        _binding.restaurantAddressEditText.editText?.afterTextChanged(_binding.restaurantAddressEditText)
        _binding.restaurantDeliveryInfoEditText.editText?.afterTextChanged(_binding.restaurantDeliveryInfoEditText)

        _binding.restaurantOpenHourEditText.setOnClickListener {
            setRestaurantOpenHour()
        }

        _binding.restaurantCloseHourEditText.setOnClickListener {
            setRestaurantCloseHour()
        }

        _binding.addRestaurantButton.setOnClickListener {
            addRestaurant()
        }
    }

    private fun initializeCitySpinner() {
        val cities = resources.getStringArray(R.array.Cities)
        val adapter = ArrayAdapter(
            activity as AppCompatActivity,
            android.R.layout.simple_spinner_dropdown_item,
            cities
        )
        _binding.citySpinner.adapter = adapter
    }

    private fun initializeCuisineSpinner() {
        val cities = resources.getStringArray(R.array.Cuisines)
        val adapter = ArrayAdapter(
            activity as AppCompatActivity,
            android.R.layout.simple_spinner_dropdown_item,
            cities
        )
        _binding.cuisineSpinner.adapter = adapter
    }

    private fun initializePaymentMethodsSpinner() {
        val paymentMethods = resources.getStringArray(R.array.RestaurantPaymentMethods).toList()
        _binding.multiSelectionSpinner.items = paymentMethods
    }

    private fun setRestaurantOpenHour() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            _binding.restaurantOpenHourEditText.setText(
                SimpleDateFormat("HH:mm", Locale.US).format(
                    cal.time
                )
            )
        }
        TimePickerDialog(
            activity,
            R.style.DialogTheme,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun setRestaurantCloseHour() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            _binding.restaurantCloseHourEditText.setText(
                SimpleDateFormat(
                    "HH:mm",
                    Locale.US
                ).format(cal.time)
            )
        }
        TimePickerDialog(
            activity,
            R.style.DialogTheme,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun addRestaurant() {
        if(hasEmptyFields())
            return

        val name = _binding.restaurantNameEditText.editText?.text.toString()
        val cuisine = _binding.cuisineSpinner.selectedItem.toString()
        val deliveryInfo = _binding.restaurantDeliveryInfoEditText.editText?.text.toString()
        val deliveryTime = _binding.restaurantDeliveryTimeEditText.text.toString()
        val address = _binding.restaurantAddressEditText.editText?.text.toString()
        val district = _binding.citySpinner.selectedItem.toString()
        val minDeliveryFee = _binding.restaurantDeliveryFeeEditText.text.toString()
        val paymentMethods = _binding.multiSelectionSpinner.selectedItems.joinToString("-")
        val phone = _binding.restaurantPhoneEditText.editText?.text.toString()
        val website = _binding.restaurantWebsiteEditText.editText?.text.toString()

        viewModel.addRestaurant(
            name,
            cuisine,
            deliveryInfo,
            deliveryTime,
            "https://firebasestorage.googleapis.com/v0/b/fooddeliveryapp-fe5bf.appspot.com/o/images%2FRestaurant2.jpg?alt=media&token=3d28246b-52b4-4030-80bf-84799080fdf7",
            address,
            district,
            minDeliveryFee,
            paymentMethods,
            phone,
            website
        )
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Log.i(RestaurantAddFragment::class.java.name, it.message.toString())
                        _binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS -> {
                        Log.i(RestaurantAddFragment::class.java.name, it.message.toString())
                        _binding.progressBar.gone()
                        findNavController().navigate(R.id.action_restaurantAddFragment_to_homeFragment)
                    }
                    Resource.Status.ERROR -> {
                        Log.e(RestaurantAddFragment::class.java.name, it.message.toString())
                        _binding.progressBar.gone()
                        Toast.makeText(context, "Operation Failed", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_restaurantAddFragment_to_homeFragment)
                    }
                }
            })
    }

    private fun hasEmptyFields() : Boolean{
        if(_binding.restaurantNameEditText.editText?.text.isNullOrEmpty()){
            _binding.restaurantNameEditText.error = "This can't be empty!"
            return true
        } else {
            _binding.restaurantNameEditText.error = null
        }

        if(_binding.restaurantPhoneEditText.editText?.text.isNullOrEmpty()){
            _binding.restaurantPhoneEditText.error = "This can't be empty!"
            return true
        } else {
            _binding.restaurantPhoneEditText.error = null
        }

        if(_binding.restaurantWebsiteEditText.editText?.text.isNullOrEmpty()){
            _binding.restaurantWebsiteEditText.error = "This can't be empty!"
            return true
        } else {
            _binding.restaurantWebsiteEditText.error = null
        }

        if(_binding.restaurantOpenHourEditText.text.isNullOrEmpty()){
            _binding.errorTextView.visibility = View.VISIBLE
            _binding.errorTextView.text = getString(R.string.restaurant_open_hour_error)
            return true
        } else {
            _binding.errorTextView.visibility = View.GONE
        }

        if(_binding.restaurantCloseHourEditText.text.isNullOrEmpty()){
            _binding.errorTextView.visibility = View.VISIBLE
            _binding.errorTextView.text = getString(R.string.restaurant_close_hour_error)
            return true
        } else {
            _binding.errorTextView.visibility = View.GONE
        }

        if(_binding.restaurantAddressEditText.editText?.text.isNullOrEmpty()){
            _binding.restaurantAddressEditText.error = "This can't be empty!"
            return true
        } else {
            _binding.restaurantAddressEditText.error = null
        }

        if(_binding.restaurantDeliveryTimeLayout.editText?.text.isNullOrEmpty()){
            _binding.restaurantDeliveryTimeLayout.error = "Empty!"
            return true
        } else {
            _binding.restaurantDeliveryTimeLayout.error = null
        }

        if(_binding.restaurantDeliveryFeeLayout.editText?.text.isNullOrEmpty()){
            _binding.restaurantDeliveryFeeLayout.error = "Empty!"
            return true
        } else {
            _binding.restaurantDeliveryFeeLayout.error = null
        }

        if(_binding.restaurantDeliveryInfoEditText.editText?.text.isNullOrEmpty()){
            _binding.restaurantDeliveryInfoEditText.error = "This can't be empty!"
            return true
        } else {
            _binding.restaurantDeliveryInfoEditText.error = null
        }

        if(_binding.multiSelectionSpinner.selectedItems == null || _binding.multiSelectionSpinner.selectedItems.size <= 0){
            _binding.errorTextView.visibility = View.VISIBLE
            _binding.errorTextView.text = getString(R.string.restaurant_payment_methods_error)
            return true
        } else {
            _binding.errorTextView.visibility = View.GONE
        }

        return false
    }
}


