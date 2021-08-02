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
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.R
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class RestaurantAddFragment : Fragment() {

    private lateinit var addRestaurantImageView: ImageView
    private lateinit var openTxt: TextInputEditText
    private lateinit var closeTxt: TextInputEditText
    private lateinit var spinner: Spinner
    private lateinit var addRestaurantButton : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayHomeAsUpEnabled(true)
        }

        addViews(view)
        addListeners()
        initializeSpinner()
    }

    private fun addViews(view: View) {
        addRestaurantImageView = view.findViewById(R.id.addRestaurantLogo)
        openTxt = view.findViewById(R.id.restaurantOpenHourEditText)
        closeTxt = view.findViewById(R.id.restaurantCloseHourEditText)
        addRestaurantButton = view.findViewById(R.id.addRestaurantButton)
        spinner = view.findViewById(R.id.citySpinner)
    }

    private fun addListeners() {
        addRestaurantImageView.setOnClickListener {
            addRestaurantImage()
        }

        openTxt.setOnClickListener {
            setRestaurantOpenHour()
        }

        closeTxt.setOnClickListener {
            setRestaurantCloseHour()
        }

        addRestaurantButton.setOnClickListener {
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
        spinner.adapter = adapter
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
            openTxt.setText(SimpleDateFormat("HH:mm", Locale.US).format(cal.time))
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
            closeTxt.setText(SimpleDateFormat("HH:mm", Locale.US).format(cal.time))
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
                Picasso.get().load(selectedImage).fit().centerCrop().into(addRestaurantImageView)
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