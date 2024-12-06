package com.example.foodforeveryone.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodforeveryone.model.CartItem
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class CartViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData for cart items and empty state
    val cartItems = MutableLiveData<MutableList<CartItem>>()
    val isCartEmpty = MutableLiveData<Boolean>()

    private val sharedPreferences = application.getSharedPreferences("CartPrefs", Application.MODE_PRIVATE)

    init {
        loadCartItems()
    }

    // Function to load cart items from SharedPreferences
    private fun loadCartItems() {
        viewModelScope.launch {
            val cartData = sharedPreferences.getStringSet("cartItems", setOf()) ?: setOf()
            val items = cartData.map {
                val parts = it.split("|")
                val imageBytes = Base64.decode(parts[2], Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                CartItem(parts[0], parts[1], bitmap)
            }.toMutableList()

            cartItems.value = items
            isCartEmpty.value = items.isEmpty()
        }
    }

    // Function to remove item from cart
    fun removeItem(position: Int) {
        val currentItems = cartItems.value?.toMutableList()
        currentItems?.let {
            it.removeAt(position)
            cartItems.value = it
            updateSharedPreferences(it)
            isCartEmpty.value = it.isEmpty()
        }
    }

    // Function to update SharedPreferences after cart change
    private fun updateSharedPreferences(items: MutableList<CartItem>) {
        val updatedCartData = items.map {
            "${it.name}|${it.price}|${it.image?.let { encodeImageToBase64(it) }}"
        }.toSet()

        sharedPreferences.edit().putStringSet("cartItems", updatedCartData).apply()
    }

    // Helper function to encode image to Base64
    private fun encodeImageToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }
}
