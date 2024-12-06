package com.example.foodforeveryone.view.activity




import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodforeveryone.databinding.ActivityFavouriteBinding
import com.example.foodforeveryone.view.adapter.FavAdapter
import com.example.foodforeveryone.viewmodel.FavouriteViewModel

class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteBinding
    private val favouriteViewModel: FavouriteViewModel by viewModels()

    private lateinit var foodAdapter: FavAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set back button listener
        binding.back.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        // Observe the favorites list from ViewModel
        favouriteViewModel.favItems.observe(this, Observer { favItems ->
            foodAdapter = FavAdapter(favItems) { position ->
                favouriteViewModel.deleteFavorite(position)
            }
            binding.fav.layoutManager = LinearLayoutManager(this)
            binding.fav.adapter = foodAdapter

            // Observe the empty state and update the "No items" message visibility
            toggleNoItemsMessage(favItems.isEmpty())
        })

        // Observe the isEmpty state
        favouriteViewModel.isEmpty.observe(this, Observer { isEmpty ->
            toggleNoItemsMessage(isEmpty)
        })
    }

    private fun toggleNoItemsMessage(isEmpty: Boolean) {
        if (isEmpty) {
            binding.fav.visibility = View.GONE
            binding.noItemsText.visibility = View.VISIBLE
        } else {
            binding.fav.visibility = View.VISIBLE
            binding.noItemsText.visibility = View.GONE
        }
    }
}
