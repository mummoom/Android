package com.mummoom.md.ui.main.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.remote.Ingredients.IngredientsService
import com.mummoom.md.data.remote.Ingredients.IngredientsView
import com.mummoom.md.databinding.ActivityFoodtypeBinding
import com.mummoom.md.ui.BaseActivity

class FoodtypeActivity : BaseActivity<ActivityFoodtypeBinding>(ActivityFoodtypeBinding::inflate),IngredientsView{

    private lateinit var foodtypeRVAdapter: FoodtypeRVAdapter
    private var categoryNum: Int = -1

    override fun initAfterBinding() {

        binding.foodtypeInfoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.foodtypeBackIv.setOnClickListener {
            finish()
        }

    }


    private fun initRecyclerView(){
        foodtypeRVAdapter = FoodtypeRVAdapter(this)
        binding.foodtypeInfoRv.adapter = foodtypeRVAdapter

        foodtypeRVAdapter.setMyItemClickListener(object : FoodtypeRVAdapter.MyItemClickListener{

            override fun onItemClick(ingredients: Ingredients) {
                val intent = Intent(this@FoodtypeActivity, FoodinfoActivity::class.java)

                intent.putExtra("ingredientInfo", ingredients)
                startActivity(intent)
            }

        })

    }


    private fun getFoodtype(){

        Log.d("categoryNum",categoryNum.toString())
        val foodtypeService = IngredientsService()
        foodtypeService.setIngredientsView(this)
        foodtypeService.getInfoByCategory(categoryNum)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.foodtypeTitleTv.text = intent.getStringExtra("category")

        if(intent.hasExtra("categoryNum")){
            categoryNum = intent.getIntExtra("categoryNum",-1)

        }
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        getFoodtype()
    }

    override fun onIngredientsLoading() {
        TODO("Not yet implemented")
    }

    override fun onIngredientsSuccess(ingredients: ArrayList<Ingredients>) {
        foodtypeRVAdapter.addIngredients(ingredients)
        Log.d("ingredients",ingredients.toString())
    }

    override fun onIngredientsFailure(code: Int, message: String) {
        when(code){
            400 -> Log.d("fail", message)
        }
    }


}