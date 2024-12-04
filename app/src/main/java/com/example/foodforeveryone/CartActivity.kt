package com.example.foodforeveryone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

data class CartItem(val name: String, val price: String)

class CartActivity : AppCompatActivity() {

    lateinit var backButton:ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: CartAdapter
    private lateinit var noItemsTextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        backButton = findViewById(R.id.back)
        noItemsTextView = findViewById(R.id.no_items_text)


        backButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
            val sharedPref = getSharedPreferences("CartPrefs", MODE_PRIVATE)
            val cartData = sharedPref.getStringSet("cartItems", setOf()) ?: setOf()

// Convert data to list of cart items
            val cartItems = cartData.map {
                val parts = it.split("|")
                CartItem(parts[0], parts[1]) // Assuming a CartItem class with name and price
            }.toMutableList()
        foodAdapter = CartAdapter(cartItems) { position ->
            if (position in cartItems.indices) {
                // Remove item from list
                cartItems.removeAt(position)
                foodAdapter.notifyItemRemoved(position)

                // Notify RecyclerView of changes to avoid duplicates
                foodAdapter.notifyItemRangeChanged(position, cartItems.size)

                // Update SharedPreferences
                val updatedCartData = cartItems.map { "${it.name}|${it.price}" }.toSet()
                sharedPref.edit().putStringSet("cartItems", updatedCartData).apply()

                // Check if the list is empty and toggle the "No items in cart" message
                toggleNoItemsMessage(cartItems.isEmpty(), recyclerView, noItemsTextView)
            }
        }

        recyclerView = findViewById(R.id.cart_recycler)
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