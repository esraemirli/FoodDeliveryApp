package com.example.fooddeliveryapp.ui.mealadd

import com.example.fooddeliveryapp.model.entity.Ingredient

interface IngredientRecyclerViewAdapterListener {
    fun onIngredientClickListener(ingredient : Ingredient, position : Int)
}