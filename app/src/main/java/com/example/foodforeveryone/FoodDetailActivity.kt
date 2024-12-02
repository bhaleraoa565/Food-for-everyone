package com.example.foodforeveryone


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var back: ImageView
    private lateinit var foodName: TextView
    private lateinit var foodPrice: TextView
    private lateinit var foodImage: ImageView
    private lateinit var foodInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)

        // Initialize views
        back = findViewById(R.id.back)
        foodName = findViewById(R.id.food_name)
        foodPrice = findViewById(R.id.food_price)
        foodImage = findViewById(R.id.food_image)
        foodInfo = findViewById(R.id.info_food)

        // Set back button click listener
        back.setOnClickListener {
            onBackPressed()
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
