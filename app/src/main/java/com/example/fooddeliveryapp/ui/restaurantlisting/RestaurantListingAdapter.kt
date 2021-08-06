package com.example.fooddeliveryapp.ui.restaurantlisting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant

class RestaurantListingAdapter : RecyclerView.Adapter<RestaurantListingAdapter.ViewHolder>() {

    private lateinit var restaurantList: List<Restaurant>
    private var listener: RestaurantListingAdapterListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val containerFrameLayout: FrameLayout = view.findViewById(R.id.containerFrameLayout)
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val locationTextView: TextView = view.findViewById(R.id.locationTextView)
        private val imageView: AppCompatImageView = view.findViewById(R.id.imageView)

        fun bind(restaurant: Restaurant, listener: RestaurantListingAdapterListener?) {
            nameTextView.text = restaurant.name
            locationTextView.text = restaurant.district
//            Glide.with(imageView.context)
//                .load(item.image).into(imageView);

            containerFrameLayout.setOnClickListener {
                listener?.onRestaurantClickListener(restaurant)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = restaurantList.size

    fun setData(restaurantList: List<Restaurant>) {
        this.restaurantList = restaurantList
        notifyDataSetChanged()
    }

    fun addListener(listener: RestaurantListingAdapterListener?) {
        this.listener = listener
    }

}