package com.mummoom.md.ui.main.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.databinding.ItemFoodBinding

class FoodtypeRVAdapter(val context: Context) : RecyclerView.Adapter<FoodtypeRVAdapter.ViewHolder>(){

    private val ingredientsList = ArrayList<Ingredients>()

    //fooditem 클릭리스너
    interface MyItemClickListener{
        fun onItemClick(ingredients : Ingredients)
    }

    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    //뷰홀더 객체 생성
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FoodtypeRVAdapter.ViewHolder {
        val binding: ItemFoodBinding = ItemFoodBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(binding)
    }

    //아이템이 들어올떄마다 거쳐가는곳
    //포지션에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    override fun onBindViewHolder(holder: FoodtypeRVAdapter.ViewHolder, position: Int) {
        Log.d("bindviewholder","success")
        holder.bind(ingredientsList[position])
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemClick(ingredientsList[position])
        }

    }
    //전체 아이템 개수 리턴
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
            //발바닥_score
            if(ingredients.score == 1){
                binding.foodFootLl1.visibility = View.VISIBLE
                binding.foodFootLl2.visibility = View.GONE
                binding.foodFootLl3.visibility = View.GONE
                binding.foodFootLl4.visibility = View.GONE
                binding.foodFootLl5.visibility = View.GONE
            }
            else if(ingredients.score == 2){
                binding.foodFootLl1.visibility = View.GONE
                binding.foodFootLl2.visibility = View.VISIBLE
                binding.foodFootLl3.visibility = View.GONE
                binding.foodFootLl4.visibility = View.GONE
                binding.foodFootLl5.visibility = View.GONE
            }
            else if(ingredients.score == 3){
                binding.foodFootLl1.visibility = View.GONE
                binding.foodFootLl2.visibility = View.GONE
                binding.foodFootLl3.visibility = View.VISIBLE
                binding.foodFootLl4.visibility = View.GONE
                binding.foodFootLl5.visibility = View.GONE
            }
            else if(ingredients.score == 4){
                binding.foodFootLl1.visibility = View.GONE
                binding.foodFootLl2.visibility = View.GONE
                binding.foodFootLl3.visibility = View.GONE
                binding.foodFootLl4.visibility = View.VISIBLE
                binding.foodFootLl5.visibility = View.GONE
            }
            else if(ingredients.score == 5){
                binding.foodFootLl1.visibility = View.GONE
                binding.foodFootLl2.visibility = View.GONE
                binding.foodFootLl3.visibility = View.GONE
                binding.foodFootLl4.visibility = View.GONE
                binding.foodFootLl5.visibility = View.VISIBLE
            }
        }
    }

}