package com.example.foodforeveryone.view.fragment

import FoodAdapter
import FoodViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodforeveryone.R



class FoodsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var foodItemViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_foods, container, false)

        // Initialize the ViewModel
        foodItemViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)

        // Observe the LiveData for food items
        foodItemViewModel.foodList.observe(viewLifecycleOwner) { foodList ->
            foodAdapter.updateData(foodList) // Update RecyclerView adapter with new data
        }

        // Fetch food items (could be from API or database)
        foodItemViewModel.fetchFoodItems()

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.food_recycler)
        foodAdapter = FoodAdapter(mutableListOf(), requireContext())

        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = foodAdapter

        return view
    }
}
