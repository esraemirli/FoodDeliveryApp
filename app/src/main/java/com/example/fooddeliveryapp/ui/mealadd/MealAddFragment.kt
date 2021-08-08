package com.example.fooddeliveryapp.ui.mealadd

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
    private val args : MealAddFragmentArgs by navArgs()
    private lateinit var _binding : FragmentMealAddBinding
    private val viewModel : MealAddViewModel by viewModels()

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

    private fun addListeners(){
        _binding.addMealImageView.setOnClickListener {
            addFoodLogo()
        }

        _binding.addMealIngredientLogo.setOnClickListener {
            addFoodIngredient()
        }

        _binding.addMealButton.setOnClickListener {
            addMeal()
        }
    }

    private fun addFoodLogo() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startForResult.launch(intent)
    }

    private fun addFoodIngredient() {
        ingredientsList.add(Ingredient(_binding.mealIngredientsEditText.text.toString(), true))
        ingredientAdapter.notifyDataSetChanged()
        _binding.mealIngredientsEditText.text!!.clear()
    }

    private fun addMeal() {
        val ingredients : MutableList<String> = mutableListOf()
        val name = _binding.mealNameEditText.editText?.text.toString()
        val price = _binding.mealPriceEditText.editText?.text.toString()
        val imageUrl = "Image URL :)"

        ingredientsList.forEach {
            ingredients.add(it.ingredient)
        }

        viewModel.addMeal(args.restaurantId, name, imageUrl, price, ingredients)
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Log.i(MealAddFragment::class.java.name, it.message.toString())
                        _binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS -> {
                        Log.i(MealAddFragment::class.java.name, it.message.toString())
                        _binding.progressBar.gone()
                        val action = MealAddFragmentDirections.actionMealAddFragmentToRestaurantDetailFragment(it.data!!.message.restaurant)
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

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri = result.data?.data!!
                Glide.with(requireContext()).load(selectedImage).fitCenter().into(_binding.addMealImageView)
            }
        }
}