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
import com.example.foodforeveryone.databinding.ActivityCartBinding
import com.example.foodforeveryone.view.adapter.CartAdapter
import com.example.foodforeveryone.viewmodel.CartViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe cart items LiveData from ViewModel
        cartViewModel.cartItems.observe(this, Observer { cartItems ->
            val foodAdapter = CartAdapter(cartItems) { position ->
                cartViewModel.removeItem(position)
            }

            binding.cartRecycler.layoutManager = LinearLayoutManager(this)
            binding.cartRecycler.adapter = foodAdapter

            // Toggle "No items in cart" message
            toggleNoItemsMessage(cartItems.isEmpty())
        })

        // Observe the empty state
        cartViewModel.isCartEmpty.observe(this, Observer { isEmpty ->
            toggleNoItemsMessage(isEmpty)
        })

        binding.back.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

    private fun toggleNoItemsMessage(isEmpty: Boolean) {
        if (isEmpty) {
            binding.cartRecycler.visibility = View.GONE
            binding.noItemsText.visibility = View.VISIBLE
        } else {
            binding.cartRecycler.visibility = View.VISIBLE
            binding.noItemsText.visibility = View.GONE
        }
    }
}
