package com.example.fooddeliveryapp.utils

import com.example.fooddeliveryapp.model.Meal

interface IMealOnClick {
    fun onClick(meal: Meal)
}