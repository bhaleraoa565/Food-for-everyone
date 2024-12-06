package com.example.foodforeveryone.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodforeveryone.R
import com.example.foodforeveryone.model.FavItem

class FavAdapter(
    private val items: List<FavItem>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<FavAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.food_name)
        val priceTextView: TextView = view.findViewById(R.id.food_price)
        val timeFood: TextView = view.findViewById(R.id.time)
        val imageView: ImageView = view.findViewById(R.id.food_image)
        val deleteButton: ImageView = view.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favourite_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.priceTextView.text = item.price
        holder.timeFood.text = item.foodTime
        item.image?.let { holder.imageView.setImageBitmap(it) }

        holder.deleteButton.setOnClickListener {
            onDelete(position)
        }
    }

    override fun getItemCount(): Int = items.size
}
