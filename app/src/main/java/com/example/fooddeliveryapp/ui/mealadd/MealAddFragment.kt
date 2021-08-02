package com.example.fooddeliveryapp.ui.mealadd

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.entity.Ingredient
import com.google.android.flexbox.*
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class MealAddFragment : Fragment() {

    private lateinit var addMealImageView: ImageView
    private lateinit var addIngredientImageView: ImageView
    private lateinit var ingredientText: TextInputEditText
    private lateinit var addMealButton : Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var ingredientsList: MutableList<Ingredient>
    private lateinit var ingredientAdapter: IngredientRecyclerViewAdapter
    private lateinit var layoutManager: FlexboxLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayHomeAsUpEnabled(true)
        }

        addViews(view)
        initializeRecyclerView()
        addListeners()
    }

    private fun addViews(view : View) {
        addMealImageView = view.findViewById(R.id.addMealLogo)
        addIngredientImageView = view.findViewById(R.id.addMealIngredientLogo)
        ingredientText = view.findViewById(R.id.mealIngredientsEditText)
        addMealButton = view.findViewById(R.id.addMealButton)
        recyclerView = view.findViewById(R.id.recyclerView)
    }

    private fun initializeRecyclerView() {
        ingredientsList = mutableListOf()
        ingredientAdapter = IngredientRecyclerViewAdapter(ingredientsList)

        layoutManager = FlexboxLayoutManager(activity)
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        layoutManager.alignItems = AlignItems.FLEX_START

        ingredientAdapter.addListener(object : IngredientRecyclerViewAdapterListener {
            override fun onIngredientClickListener(ingredient: Ingredient, position: Int) {
                ingredientsList.removeAt(position)
                ingredientAdapter.notifyDataSetChanged()
            }
        })

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ingredientAdapter
    }

    private fun addListeners(){
        addMealImageView.setOnClickListener {
            addFoodLogo()
        }

        addIngredientImageView.setOnClickListener {
            addFoodIngredient()
        }

        addMealButton.setOnClickListener {
            addMeal()
        }
    }

    private fun addFoodLogo() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startForResult.launch(intent)
    }

    private fun addFoodIngredient() {
        ingredientsList.add(Ingredient(ingredientText.text.toString(), true))
        ingredientAdapter.notifyDataSetChanged()
    }

    private fun addMeal() {
        // TODO : Post Meal and return RestaurantDetail?
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri = result.data?.data!!
                Picasso.get().load(selectedImage).fit().centerCrop().into(addMealImageView)
            }
        }
}