package com.example.foodforeveryone

import androidx.recyclerview.widget.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class FoodAdapter(
    private val foodList: List<FoodItemModel>,
    private val context: Context
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    // ViewHolder class to hold item views
    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImg: ImageView = itemView.findViewById(R.id.food_image)
        val hotel: TextView = itemView.findViewById(R.id.hotel_name)
        val foodName: TextView = itemView.findViewById(R.id.food_name)
        val foodPrice: TextView = itemView.findViewById(R.id.food_price)
        val time: TextView = itemView.findViewById(R.id.time)
        val ratings: TextView = itemView.findViewById(R.id.rating)
    }

    // Inflate the item layout and create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_item_layout, parent, false)
        return FoodViewHolder(view)
    }

    // Bind data to item views
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodList[position]
        holder.foodImg.setImageResource(foodItem.foodImg)
        holder.hotel.text = foodItem.hotel
        holder.foodName.text = foodItem.foodName
        holder.foodPrice.text = foodItem.foodPrice
        holder.time.text = foodItem.time
        holder.ratings.text = foodItem.rating
    }

    // Return the size of the list
    override fun getItemCount(): Int {
        return foodList.size
    }
}
