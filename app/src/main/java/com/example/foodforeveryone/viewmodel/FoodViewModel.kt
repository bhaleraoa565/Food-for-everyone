import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodforeveryone.R
import kotlinx.coroutines.launch
import com.example.foodforeveryone.model.FoodItemModel

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    val foodList: MutableLiveData<List<FoodItemModel>> = MutableLiveData()

    // Simulating data fetching, which could be from a database or network
    fun fetchFoodItems() {
        val items = listOf(
            FoodItemModel(R.drawable.chicken_biryani, "PK Biryani House", "Chicken Biryani", "Rs 100.00", "12 min - 1 km", "4.7 - 200+ ratings"),
            FoodItemModel(R.drawable.chicken_tikka, "Bubur ayam", "Chicken Tikka", "Rs 200.00", "20 min - 1.7 km", "4.0 - 200+ ratings"),
            FoodItemModel(R.drawable.chicken65, "State Kambing Park", "Chicken65", "Rs 300.00", "32 min - 5 km", "4.1 - 200+ ratings"),
            FoodItemModel(R.drawable.lolipop, "Chinese Square", "Chicken Lollipop", "Rs 150.00", "19 min - 1.5 km", "4 - 300+ ratings"),
            FoodItemModel(R.drawable.chicken_fry, "Hotel", "Chicken Fry", "Rs 400.00", "42 min - 6 km", "3.7 - 100+ ratings"),
            FoodItemModel(R.drawable.momos, "Yummy Momos", "Chicken Momos", "Rs 80.00", "12 min - 1 km", "4.7 - 200+ ratings")
        )
        // Simulate fetching data (could be from a network or database)
        foodList.value = items
    }
}
