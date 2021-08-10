package com.example.fooddeliveryapp.ui.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.entity.order.Order




class OrderRecyclerViewAdapter : RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderListViewHolder>() {
    var myOrderList = ArrayList<Order>()

    class OrderListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var orderImageView: ImageView = view.findViewById(R.id.ordersImageView)
        private var orderRestaurantName: TextView = view.findViewById(R.id.ordersItemRestaurantNameTextView)
        private var orderDate: TextView = view.findViewById(R.id.ordersItemDateTextView)
        private var orderMealName: TextView = view.findViewById(R.id.ordersItemFoodNameTextView)

        fun setItem(order : Order)
        {
            Glide.with(orderImageView.context)
                .load(order.meal.image)
                .into(orderImageView)
            orderRestaurantName.text = order.restaurant.name
            orderMealName.text = order.meal.name
            orderDate.text = order.createdDate.toString()



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_myorders, parent, false)
        return OrderListViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        var item = myOrderList[position]
        holder.setItem(item)

    }

    fun setOrderList(list: ArrayList<Order>) {
        this.myOrderList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = myOrderList.size
}