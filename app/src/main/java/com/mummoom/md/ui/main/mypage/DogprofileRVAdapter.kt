package com.mummoom.md.ui.main.mypage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.entities.Dog
import com.mummoom.md.databinding.ItemDogprofileBinding
import com.mummoom.md.databinding.ItemFoodBinding

class DogprofileRVAdapter() : RecyclerView.Adapter<DogprofileRVAdapter.ViewHolder>(){

    private val dogList = ArrayList<Dog>()


    interface MyItemClickListener{
        fun onItemClick(dog: Dog)
    }

    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    //뷰홀더 객체 생성
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DogprofileRVAdapter.ViewHolder {
        val binding: ItemDogprofileBinding = ItemDogprofileBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(binding)
    }

    //아이템이 들어올떄마다 거쳐가는곳
    //포지션에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    override fun onBindViewHolder(holder: DogprofileRVAdapter.ViewHolder, position: Int) {
        Log.d("bindviewholder","success")
        holder.bind(dogList[position])
//        holder.itemView.setOnClickListener{
//            myItemClickListener.onItemClick(dogList[position])
//        }
        holder.binding.itemDogprofileMoreBtnIv.setOnClickListener {
            myItemClickListener.onItemClick(dogList[position])
        }

    }

    //전체 아이템 개수 리턴
    override fun getItemCount(): Int = dogList.size

    fun addDogs(dog: ArrayList<Dog>){
        this.dogList.clear()
        this.dogList.addAll(dog)

        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemDogprofileBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(dog: Dog){

            binding.itemDogprofileDogNameTv.text=dog.dogName
            binding.itemDogprofileDogbirthTv.text=dog.dogBirth
            binding.itemDogprofileDogtypeTv.text=dog.dogType

            if(dog.dogSex=="0")
                binding.itemDogprofileDogGenderTv.text="남아"
            else if(dog.dogSex=="1") binding.itemDogprofileDogGenderTv.text="여아"

        }
    }

}