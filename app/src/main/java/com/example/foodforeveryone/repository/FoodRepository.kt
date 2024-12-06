package com.example.foodforeveryone.repository

import com.example.foodforeveryone.R
import com.example.foodforeveryone.model.FoodItemModel

class FoodRepository {
    fun getFoodItems(): List<FoodItemModel> {
        return listOf(
            FoodItemModel(R.drawable.chicken_biryani, "PK Biryani House", "Chicken Biryani", "Rs 100.00", "12 min - 1 km", "4.7 - 200+ ratings"),
            FoodItemModel(R.drawable.chicken_tikka, "Bubur ayam", "Chicken Tikka", "Rs 200.00", "20 min - 1.7 km", "4.0 - 200+ ratings"),
            FoodItemModel(R.drawable.chicken65, "State Kambing Park", "Chicken65", "Rs 300.00", "32 min - 5 km", "4.1 - 200+ ratings"),
            FoodItemModel(R.drawable.lolipop, "Chinese Square", "Chicken Lollipop", "Rs 150.00", "19 min - 1.5 km", "4 - 300+ ratings"),
            FoodItemModel(R.drawable.chicken_fry, "Hotel", "Chicken Fry", "Rs 400.00", "42 min - 6 km", "3.7 - 100+ ratings"),
            FoodItemModel(R.drawable.momos, "Yummy Momos", "Chicken Momos", "Rs 80.00", "12 min - 1 km", "4.7 - 200+ ratings")
        )
    }
}