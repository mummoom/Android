package com.mummoom.md.ui.main.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.databinding.ItemFoodBinding

class FoodtypeRVAdapter(val context: Context) : RecyclerView.Adapter<FoodtypeRVAdapter.ViewHolder>(){

    private val ingredientsList = ArrayList<Ingredients>()

    interface MyItemClickListener{
        fun onItemClick()
    }

    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FoodtypeRVAdapter.ViewHolder {
        val binding: ItemFoodBinding = ItemFoodBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(binding)
    }

    //아이템이 들어올떄마다 거쳐가는곳
    override fun onBindViewHolder(holder: FoodtypeRVAdapter.ViewHolder, position: Int) {
        Log.d("bindviewholder","success")
        holder.bind(ingredientsList[position])
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemClick()
        }

    }

    override fun getItemCount(): Int = ingredientsList.size

    fun addIngredients(ingredients: ArrayList<Ingredients>){
        this.ingredientsList.clear()
        this.ingredientsList.addAll(ingredients)

        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(ingredients: Ingredients){
            Glide.with(context)
                .load(ingredients.imgUrl)
                .into(binding.foodIv)

            binding.foodNameTv.text = ingredients.name
            binding.foodKcalTv.text = ingredients.kcal.toString()+" kcal"
        }
    }

}