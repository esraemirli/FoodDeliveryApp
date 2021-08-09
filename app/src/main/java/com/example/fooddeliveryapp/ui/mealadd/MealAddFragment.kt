package com.example.fooddeliveryapp.ui.mealadd

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentMealAddBinding
import com.example.fooddeliveryapp.model.entity.Ingredient
import com.example.fooddeliveryapp.utils.Resource
import com.example.fooddeliveryapp.utils.gone
import com.example.fooddeliveryapp.utils.show
import com.google.android.flexbox.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealAddFragment : Fragment() {
    private val args: MealAddFragmentArgs by navArgs()
    private lateinit var _binding: FragmentMealAddBinding
    private val viewModel: MealAddViewModel by viewModels()

    private lateinit var ingredientsList: MutableList<Ingredient>
    private lateinit var ingredientAdapter: IngredientRecyclerViewAdapter
    private lateinit var layoutManager: FlexboxLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealAddBinding.inflate(inflater, container, false)
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
            .load("https://firebasestorage.googleapis.com/v0/b/fooddeliveryapp-fe5bf.appspot.com/o/images%2Fpizza.jpg?alt=media&token=7ffc6831-d9ae-4e9a-96a9-7898bc546878")
            .into(_binding.addMealImageView)
        initializeRecyclerView()
        addListeners()
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

        _binding.recyclerView.layoutManager = layoutManager
        _binding.recyclerView.adapter = ingredientAdapter
    }

    private fun addListeners() {


        _binding.addMealIngredientLogo.setOnClickListener {
            addFoodIngredient()
        }

        _binding.addMealButton.setOnClickListener {
            addMeal()
        }
    }


    private fun addFoodIngredient() {
        ingredientsList.add(Ingredient(_binding.mealIngredientsEditText.text.toString(), true))
        ingredientAdapter.notifyDataSetChanged()
        _binding.mealIngredientsEditText.text!!.clear()
    }

    private fun addMeal() {
        if(checkEmptyTextFields())
            return

        val ingredients: MutableList<String> = mutableListOf()
        val name = _binding.mealNameEditText.editText?.text.toString()
        val price = _binding.mealPriceEditText.editText?.text.toString()

        ingredientsList.forEach {
            ingredients.add(it.ingredient)
        }

        viewModel.addMeal(
            args.restaurantId,
            name,
            "https://firebasestorage.googleapis.com/v0/b/fooddeliveryapp-fe5bf.appspot.com/o/images%2Fpizza.jpg?alt=media&token=7ffc6831-d9ae-4e9a-96a9-7898bc546878",
            price,
            ingredients
        )
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Log.i(MealAddFragment::class.java.name, it.message.toString())
                        _binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS -> {
                        Log.i(MealAddFragment::class.java.name, it.message.toString())
                        _binding.progressBar.gone()
                        val action =
                            MealAddFragmentDirections.actionMealAddFragmentToRestaurantDetailFragment(
                                it.data!!.message.restaurant
                            )
                        findNavController().navigate(action)
                    }
                    Resource.Status.ERROR -> {
                        Log.e(MealAddFragment::class.java.name, it.message.toString())
                        _binding.progressBar.gone()
                        // TODO : error response doesn't have data, restaurant will be null. Return home page?
                    }
                }
            })
    }

    private fun checkEmptyTextFields() : Boolean {

        if(_binding.mealNameEditText.editText?.text.isNullOrEmpty()){
            _binding.mealNameEditText.error = "This can't be empty!"
            return true
        } else {
            _binding.mealNameEditText.error = null
        }

        if(_binding.mealPriceEditText.editText?.text.isNullOrEmpty()){
            _binding.mealPriceEditText.error = "This can't be empty!"
            return true
        } else {
            _binding.mealPriceEditText.error = null
        }

        if(_binding.mealDescriptionLayout.editText?.text.isNullOrEmpty()){
            _binding.mealDescriptionLayout.error = "This can't be empty!"
            return true
        } else {
            _binding.mealDescriptionLayout.error = null
        }

        if(ingredientsList.size <= 0){
            return true
        }

        return false
    }

}