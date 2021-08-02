package com.example.fooddeliveryapp.ui.foodadd

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.R

class FoodAddFragment : Fragment() {

    private lateinit var addFoodImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFoodImageView = view.findViewById(R.id.addFoodLogo)

        (activity as AppCompatActivity).supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun addFoodLogo(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startForResult.launch(intent)
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri = result.data?.data!!
                // TODO : Picasso kütüphanesi eklenecek
                //Picasso.get().load(selectedImage).fit().centerCrop().into(img)
            }
        }

    fun addFoodIngredient(view: View) {
        // TODO : ingredientList'e ingredientEditText değeri alınıp eklenecek
        // TODO : UI değişecek
    }
}