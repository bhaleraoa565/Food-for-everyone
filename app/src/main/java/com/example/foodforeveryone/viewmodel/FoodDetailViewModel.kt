package com.example.foodforeveryone.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class FoodDetailViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData to hold food data
    val foodName = MutableLiveData<String>()
    val foodPrice = MutableLiveData<String>()
    val foodInfo = MutableLiveData<String>()
    val foodImage = MutableLiveData<Bitmap>()

    // Function to handle saving food item to SharedPreferences
    fun addToCart(foodName: String, foodPrice: String, foodImage: Bitmap) {
        // Convert the image to Base64
        val outputStream = ByteArrayOutputStream()
        foodImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val imageBase64 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)

        // Save to SharedPreferences
        viewModelScope.launch {
            val sharedPref = getApplication<Application>().getSharedPreferences("CartPrefs", Application.MODE_PRIVATE)
            val editor = sharedPref.edit()

            val existingCart = sharedPref.getStringSet("cartItems", mutableSetOf()) ?: mutableSetOf()
            existingCart.add("$foodName|$foodPrice|$imageBase64") // Store as "name|price|image" format
            editor.putStringSet("cartItems", existingCart)
            editor.apply()
        }
    }

    // Function to handle adding food to favorites
    fun addToFavorites(foodName: String, foodPrice: String, foodInfo: String, foodImage: Bitmap) {
        // Convert the image to Base64
        val outputStream = ByteArrayOutputStream()
        foodImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val imageBase64 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)

        // Save to SharedPreferences
        viewModelScope.launch {
            val sharedPref = getApplication<Application>().getSharedPreferences("CartPrefs", Application.MODE_PRIVATE)
            val editor = sharedPref.edit()

            val existingFavorites = sharedPref.getStringSet("favorites", mutableSetOf()) ?: mutableSetOf()
            existingFavorites.add("$foodName|$foodPrice|$foodInfo|$imageBase64") // Store as "name|price|info|image" format
            editor.putStringSet("favorites", existingFavorites)
            editor.apply()
        }
    }
}
