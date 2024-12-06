package com.example.foodforeveryone.view.activity


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import com.example.foodforeveryone.R
import com.example.foodforeveryone.databinding.ActivityFoodDetailBinding
import com.example.foodforeveryone.viewmodel.FoodDetailViewModel
import com.qamar.curvedbottomnaviagtion.dp

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDetailBinding
    private val foodDetailViewModel: FoodDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe data from ViewModel
        foodDetailViewModel.foodName.observe(this) { foodName ->
            binding.foodName.text = foodName
        }

        foodDetailViewModel.foodPrice.observe(this) { foodPrice ->
            binding.foodPrice.text = foodPrice
        }

        foodDetailViewModel.foodInfo.observe(this) { foodInfo ->
            binding.infoFood.text = foodInfo
        }

        foodDetailViewModel.foodImage.observe(this) { foodImage ->
            binding.foodImage.setImageBitmap(foodImage)
        }

        // Retrieve data from intent
        val intent = intent
        val foodName = intent.getStringExtra("foodName")
        val foodPrice = intent.getStringExtra("foodPrice")
        val foodInfo = intent.getStringExtra("foodInfo")
        val foodImgRes = intent.getIntExtra("foodImg", R.drawable.chicken65)

        // Set data in ViewModel
        foodDetailViewModel.foodName.value = foodName
        foodDetailViewModel.foodPrice.value = foodPrice
        foodDetailViewModel.foodInfo.value = foodInfo
        foodDetailViewModel.foodImage.value = (resources.getDrawable(foodImgRes) as BitmapDrawable).bitmap

        // Set up listeners for buttons
        binding.back.setOnClickListener {
            onBackPressed()
        }

        binding.addToCart.setOnClickListener {
            val foodImage = (binding.foodImage.drawable as BitmapDrawable).bitmap
            foodDetailViewModel.addToCart(foodName ?: "", foodPrice ?: "", foodImage)
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        binding.fav.setOnClickListener {
            val foodImage = (binding.foodImage.drawable as BitmapDrawable).bitmap
            foodDetailViewModel.addToFavorites(foodName ?: "", foodPrice ?: "", foodInfo ?: "", foodImage)
            val intent = Intent(this, FavouriteActivity::class.java)
            startActivity(intent)
        }
    }
}
