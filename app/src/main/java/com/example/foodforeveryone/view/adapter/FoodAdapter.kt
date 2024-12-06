import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodforeveryone.databinding.FoodItemLayoutBinding
import com.example.foodforeveryone.model.FoodItemModel
import android.content.Intent
import com.example.foodforeveryone.view.activity.FoodDetailActivity

class FoodAdapter(
    private var foodList: List<FoodItemModel>,
    private val context: Context
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    // ViewHolder class using ViewBinding
    class FoodViewHolder(val binding: FoodItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // Create ViewHolder by inflating the layout with ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = FoodItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return FoodViewHolder(binding)
    }

    // Bind data to the item views using ViewBinding
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodList[position]
        holder.binding.foodImage.setImageResource(foodItem.foodImg)
        holder.binding.hotelName.text = foodItem.hotel
        holder.binding.foodName.text = foodItem.foodName
        holder.binding.foodPrice.text = foodItem.foodPrice
        holder.binding.time.text = foodItem.time
        holder.binding.rating.text = foodItem.rating

        holder.itemView.setOnClickListener {
            val intent = Intent(context, FoodDetailActivity::class.java).apply {
                putExtra("foodName", foodList[position].foodName)
                putExtra("foodPrice", foodList[position].foodPrice)
                putExtra("foodImg", foodList[position].foodImg)
                putExtra("foodInfo", foodList[position].time)
            }
            context.startActivity(intent)
        }
    }

    // Return the size of the food list
    override fun getItemCount(): Int = foodList.size

    // Update the food list in the adapter
    fun updateData(newFoodList: List<FoodItemModel>) {
        foodList = newFoodList
        notifyDataSetChanged()
    }
}
