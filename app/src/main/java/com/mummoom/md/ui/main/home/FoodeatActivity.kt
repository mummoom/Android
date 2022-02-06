package com.mummoom.md.ui.main.home

import android.R
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.remote.Ingredients.IngredientsService
import com.mummoom.md.data.remote.Ingredients.IngredientsView
import com.mummoom.md.databinding.ActivityFoodeatBinding
import com.mummoom.md.ui.BaseActivity


class FoodeatActivity : BaseActivity<ActivityFoodeatBinding>(ActivityFoodeatBinding::inflate), IngredientsView {

    private lateinit var foodeatRVAdapter: FoodeatRVAdapter
    private var level: Int = -1

    override fun initAfterBinding() {

        binding.foodeatInfoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.foodeatBackIv.setOnClickListener {
            finish()
        }

    }
    private fun initRecyclerView(){
        foodeatRVAdapter = FoodeatRVAdapter(this)
        binding.foodeatInfoRv.adapter = foodeatRVAdapter

        foodeatRVAdapter.setMyItemClickListener(object : FoodeatRVAdapter.MyItemClickListener{

            override fun onItemClick(ingredients: Ingredients) {
                val intent = Intent(this@FoodeatActivity, FoodinfoActivity::class.java)

                intent.putExtra("ingredientInfo", ingredients)
                startActivity(intent)
            }

        })

    }

    private fun getFoodeat(){
        val foodeatService = IngredientsService()
        foodeatService.setIngredientsView(this)
        foodeatService.getInfoBySimple(level)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //제목 받기
        binding.foodeatTitleTv.text = intent.getStringExtra("category")
        //아이콘 이미지 받기
        val byteArray = intent.getByteArrayExtra("image")
        val image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
        binding.foodeatIcIv.setImageBitmap(image)
        //내용 받기
        if(intent.hasExtra("level")){
            level = intent.getIntExtra("level",-1)
        }
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        getFoodeat()
    }

    override fun onIngredientsLoading() {
        TODO("Not yet implemented")
    }

    override fun onIngredientsSuccess(ingredients: ArrayList<Ingredients>) {
        foodeatRVAdapter.addIngredients(ingredients)
    }

    override fun onIngredientsFailure(code: Int, message: String) {
        when(code){
            400 -> Log.d("fail", message)
        }
    }
}