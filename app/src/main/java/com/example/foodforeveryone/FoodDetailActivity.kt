package com.example.foodforeveryone


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.google.gson.Gson


class FoodDetailActivity : AppCompatActivity() {

    private lateinit var back: ImageView
    private lateinit var foodName: TextView
    private lateinit var foodPrice: TextView
    private lateinit var foodImage: ImageView
    private lateinit var foodInfo: TextView
    private lateinit var addToCart: TextView
    private lateinit var favourite:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)

        // Initialize views
        back = findViewById(R.id.back)
        foodName = findViewById(R.id.food_name)
        foodPrice = findViewById(R.id.food_price)
        foodImage = findViewById(R.id.food_image)
        foodInfo = findViewById(R.id.info_food)
        addToCart = findViewById(R.id.add_to_cart)
        favourite = findViewById(R.id.fav)


        // Set back button click listener
        back.setOnClickListener {
            onBackPressed()
        }
        addToCart.setOnClickListener {
            val foodName = findViewById<TextView>(R.id.food_name).text.toString()
            val foodPrice = findViewById<TextView>(R.id.food_price).text.toString()

            // Save to SharedPreferences
            val sharedPref = getSharedPreferences("CartPrefs", MODE_PRIVATE)
            val editor = sharedPref.edit()

            val existingCart = sharedPref.getStringSet("cartItems", mutableSetOf()) ?: mutableSetOf()
            existingCart.add("$foodName|$foodPrice") // Store as "name|price" format
            editor.putStringSet("cartItems", existingCart)
            editor.apply()

            // Navigate to CartActivity
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        favourite.setOnClickListener {
            val foodName = findViewById<TextView>(R.id.food_name).text.toString()
            val foodPrice = findViewById<TextView>(R.id.food_price).text.toString()
            val foodInfo = findViewById<TextView>(R.id.info_food).text.toString()

            // Save to SharedPreferences
            val sharedPref = getSharedPreferences("CartPrefs", MODE_PRIVATE)
            val editor = sharedPref.edit()

            val existingCart = sharedPref.getStringSet("cartItems", mutableSetOf()) ?: mutableSetOf()
            existingCart.add("$foodName|$foodPrice|$foodInfo") // Store as "name|price" format
            editor.putStringSet("cartItems", existingCart)
            editor.apply()

            // Navigate to CartActivity
            val intent = Intent(this, FavouriteActivity::class.java)
            startActivity(intent)
        }

        // Retrieve intent data
        val intent = intent
        foodName.text = intent.getStringExtra("foodName")
        foodPrice.text = intent.getStringExtra("foodPrice")
        foodInfo.text = intent.getStringExtra("foodInfo")
        foodImage.setImageResource(
            intent.getIntExtra("foodImg", R.drawable.chicken65) // Default image if no extra is passed
        )
    }
}
