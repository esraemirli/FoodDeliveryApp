package com.example.fooddeliveryapp.ui.mealadd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.entity.Ingredient

class IngredientRecyclerViewAdapter(private val ingredientList: MutableList<Ingredient>) : RecyclerView.Adapter<IngredientRecyclerViewAdapter.ModelViewHolder>() {

    private var ingredientClickListener : IngredientRecyclerViewAdapterListener? = null

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ingredientLayout: CardView = view.findViewById(R.id.ingredientLayout)
        private val ingredientName: TextView = view.findViewById(R.id.ingredientNameTextView)

        fun bindItems(ingredient: Ingredient,position: Int, listener: IngredientRecyclerViewAdapterListener?) {
            ingredientName.text = ingredient.ingredient
            ingredientLayout.setOnClickListener{
                listener?.onIngredientClickListener(ingredient, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient_card, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(ingredientList[position],position, ingredientClickListener)
    }

    fun addListener(listener : IngredientRecyclerViewAdapterListener) {
        ingredientClickListener = listener
    }
}