package com.example.foodforeveryone.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodforeveryone.model.FavItem
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("CartPrefs", Application.MODE_PRIVATE)

    // LiveData to hold the list of favorite items
    private val _favItems = MutableLiveData<List<FavItem>>()
    val favItems: LiveData<List<FavItem>> = _favItems

    // LiveData to handle if there are no items in the favorites list
    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    init {
        loadFavorites()
    }

    // Function to load favorites from SharedPreferences
    private fun loadFavorites() {
        viewModelScope.launch {
            val favData = sharedPreferences.getStringSet("favorites", setOf()) ?: setOf()
            val items = favData.mapNotNull {
                val parts = it.split("|")
                if (parts.size == 4) {  // Check if there are 4 parts (name, price, time, imageBase64)
                    val imageBase64 = parts[3]
                    val imageBytes = Base64.decode(imageBase64, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    FavItem(parts[0], parts[1], parts[2], bitmap) // Create FavItem with image
                } else {
                    null
                }
            }
            _favItems.value = items
            _isEmpty.value = items.isEmpty()
        }
    }

    // Function to delete a favorite item
    fun deleteFavorite(position: Int) {
        val currentItems = _favItems.value?.toMutableList()
        currentItems?.let {
            it.removeAt(position)
            _favItems.value = it
            updateSharedPreferences(it)
            _isEmpty.value = it.isEmpty()
        }
    }

    // Function to update SharedPreferences after deleting a favorite item
    private fun updateSharedPreferences(items: List<FavItem>) {
        val updatedFavData = items.map {
            "${it.name}|${it.price}|${it.foodTime}|${encodeImageToBase64(it.image)}"
        }.toSet()

        sharedPreferences.edit().putStringSet("favorites", updatedFavData).apply()
    }

    // Helper function to encode image to Base64
    private fun encodeImageToBase64(bitmap: Bitmap?): String {
        return if (bitmap != null) {
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
        } else {
            ""  // Return empty string if no image is available
        }
    }
}
