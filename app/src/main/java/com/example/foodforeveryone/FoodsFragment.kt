package com.example.foodforeveryone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FoodsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var list: MutableList<FoodItemModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_foods, container, false)

        list = mutableListOf()
        list.add(FoodItemModel(R.drawable.chicken_biryani, "PK Biryani House", "Chicken Biryani","Rs 100.00","12 min - 1 km","4.7 - 200+ ratings"))
        list.add(FoodItemModel(R.drawable.chicken_tikka, "Bubur ayam", "Chicken Tikka","Rs 200.00","20 min - 1.7 km","4.0 - 200+ ratings"))
        list.add(FoodItemModel(R.drawable.chicken65, "State Kambing Park", "Chicken65","Rs 300.00","32 min - 5 km","4.1 - 200+ ratings"))
        list.add(FoodItemModel(R.drawable.group, "PK Biryani House", "Chicken Biryani","Rs 100.00","12 min - 1 km","4.7 - 200+ ratings"))
        list.add(FoodItemModel(R.drawable.group, "PK Biryani House", "Chicken Biryani","Rs 100.00","12 min - 1 km","4.7 - 200+ ratings"))
        list.add(FoodItemModel(R.drawable.group, "PK Biryani House", "Chicken Biryani","Rs 100.00","12 min - 1 km","4.7 - 200+ ratings"))

        recyclerView = view.findViewById(R.id.food_recycler)
        foodAdapter = FoodAdapter(list, requireContext())

        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = foodAdapter

        return view
    }
}
