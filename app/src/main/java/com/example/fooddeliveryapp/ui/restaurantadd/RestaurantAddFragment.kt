package com.example.fooddeliveryapp.ui.restaurantadd

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentRestaurantAddBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class RestaurantAddFragment : Fragment() {

    private lateinit var _binding : FragmentRestaurantAddBinding

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

        addListeners()
        initializeSpinner()
    }

    private fun addListeners() {
        _binding.addRestaurantLogo.setOnClickListener {
            addRestaurantImage()
        }

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

    private fun initializeSpinner() {
        val cities = resources.getStringArray(R.array.Cities)
        val adapter = ArrayAdapter(
            activity as AppCompatActivity,
            android.R.layout.simple_spinner_dropdown_item,
            cities
        )
        _binding.citySpinner.adapter = adapter
    }

    private fun addRestaurantImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startForResult.launch(intent)
    }

    private fun setRestaurantOpenHour() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            _binding.restaurantOpenHourEditText.setText(SimpleDateFormat("HH:mm", Locale.US).format(cal.time))
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
            _binding.restaurantCloseHourEditText.setText(SimpleDateFormat("HH:mm", Locale.US).format(cal.time))
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

    private fun addRestaurant(){
        // TODO : POST Restaurant and return homePage
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri = result.data?.data!!
                Picasso.get().load(selectedImage).fit().centerCrop().into(_binding.addRestaurantLogo)
            }
        }

    /*
    //TODO : Boşluğa tıklandığında klavyeyi saklamak için
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
    */

}