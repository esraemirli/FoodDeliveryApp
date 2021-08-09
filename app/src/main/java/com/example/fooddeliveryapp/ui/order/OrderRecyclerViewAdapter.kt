package com.example.fooddeliveryapp.ui.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.entity.Order

class OrderRecyclerViewAdapter  : RecyclerView.Adapter<OrderRecyclerViewAdapter.ListViewHolder>() {
    var list = ArrayList<Order>()
    class  ListViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        fun bind(){}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_myorders,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var item = list[position]
        holder.bind()
    }

    fun setOrderData(list: ArrayList<Order>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}