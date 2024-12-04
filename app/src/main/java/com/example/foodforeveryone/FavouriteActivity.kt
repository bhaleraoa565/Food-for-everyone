package com.example.foodforeveryone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
data class FavItem(val name: String, val price: String , val foodTime:String)
class FavouriteActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FavAdapter
    private lateinit var back:ImageView
    private lateinit var noItemsTextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        noItemsTextView = findViewById(R.id.no_items_text)

        back = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this , DashboardActivity::class.java)
            startActivity(intent)
        }

        val sharedPref = getSharedPreferences("CartPrefs", MODE_PRIVATE)
        val cartData = sharedPref.getStringSet("cartItems", setOf()) ?: setOf()

// Convert data to list of cart items
        val cartItems = cartData.mapNotNull {
            val parts = it.split("|")
            if (parts.size == 3) {
                FavItem(parts[0], parts[1], parts[2]) // Assuming a FavItem class with name, price, and time
            } else {
                null // Skip invalid or corrupted entries
            }
        }.toMutableList()

// Initialize adapter with lambda for deletion
        foodAdapter = FavAdapter(cartItems) { position ->
            if (position in cartItems.indices) {
                // Remove item from list
                cartItems.removeAt(position)
                foodAdapter.notifyItemRemoved(position)

                // Notify RecyclerView of changes to prevent duplicates
                foodAdapter.notifyItemRangeChanged(position, cartItems.size)

                // Update SharedPreferences
                val updatedCartData = cartItems.map { "${it.name}|${it.price}|${it.foodTime}" }.toSet()
                sharedPref.edit().putStringSet("cartItems", updatedCartData).apply()

                // Check if the list is empty and toggle the "No items" message
                toggleNoItemsMessage(cartItems.isEmpty(), recyclerView, noItemsTextView)
            }
        }
        

        recyclerView = findViewById(R.id.fav)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = foodAdapter
        toggleNoItemsMessage(cartItems.isEmpty(), recyclerView, noItemsTextView)
    }
    private fun toggleNoItemsMessage(isEmpty: Boolean, recyclerView: RecyclerView, noItemsTextView: TextView) {
        if (isEmpty) {
            recyclerView.visibility = View.GONE
            noItemsTextView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            noItemsTextView.visibility = View.GONE
        }
    }

}