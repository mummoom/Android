package com.mummoom.md.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mummoom.md.R
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.Ingredients.Search
import com.mummoom.md.data.remote.Ingredients.IngredientsService
import com.mummoom.md.data.remote.Ingredients.IngredientsView
import com.mummoom.md.data.remote.Ingredients.SearchService
import com.mummoom.md.data.remote.Ingredients.SearchView
import com.mummoom.md.databinding.ActivityFoodtypeBinding
import com.mummoom.md.ui.BaseActivity

class FoodtypeActivity : BaseActivity<ActivityFoodtypeBinding>(ActivityFoodtypeBinding::inflate),IngredientsView,SearchView{

    private lateinit var foodtypeRVAdapter: FoodtypeRVAdapter
    private var categoryNum: Int = -1
    private var ingredientName : String = ""

    override fun initAfterBinding() {

        binding.foodtypeInfoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.foodtypeBackIv.setOnClickListener {
            finish()
        }

        binding.foodtypeSearchIv.setOnClickListener{
            if(binding.foodtypeSearchEt.toString().isEmpty()){
                Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            }else{
                foodSearch()
            }
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

        val foodtypeService = IngredientsService()

        foodtypeService.setIngredientsView(this)
        foodtypeService.getInfoByCategory(categoryNum)

    }


    private fun foodSearch(){
        ingredientName = binding.foodtypeSearchEt.text.toString()

        val newSearch = Search(ingredientName)
        val searchService = SearchService()

        searchService.setSearchView(this)
        searchService.getInfoBySearch(newSearch)
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

//ingredientsView
    override fun onIngredientsLoading() {
        binding.foodtypeRotateIv.visibility = View.VISIBLE
        binding.foodtypeLoadingIv.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        binding.foodtypeRotateIv.startAnimation(animation)
    }

    override fun onIngredientsSuccess(ingredients: ArrayList<Ingredients>) {
        binding.foodtypeRotateIv.visibility = View.GONE
        binding.foodtypeLoadingIv.visibility = View.GONE
        binding.foodtypeRotateIv.clearAnimation()
        foodtypeRVAdapter.addIngredients(ingredients)
        Log.d("ingredients",ingredients.toString())
    }

    override fun onIngredientsFailure(code: Int, message: String) {
        binding.foodtypeRotateIv.visibility = View.GONE
        binding.foodtypeLoadingIv.visibility = View.GONE
        binding.foodtypeRotateIv.clearAnimation()
        when(code){
            400 -> Log.d("fail", message)
        }
    }

    //searchView
    override fun onSearchLoading() {
        binding.foodtypeRotateIv.visibility = View.VISIBLE
        binding.foodtypeLoadingIv.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        binding.foodtypeRotateIv.startAnimation(animation)
    }

    override fun onSearchSuccess(searchIngredients: ArrayList<Ingredients>) {
        binding.foodtypeRotateIv.visibility = View.GONE
        binding.foodtypeLoadingIv.visibility = View.GONE
        binding.foodtypeRotateIv.clearAnimation()
        foodtypeRVAdapter.addIngredients(searchIngredients)
    }

    override fun onSearchFailure(code: Int, message: String) {
        binding.foodtypeRotateIv.visibility = View.GONE
        binding.foodtypeLoadingIv.visibility = View.GONE
        binding.foodtypeRotateIv.clearAnimation()
        when(code){
            400 -> Log.d("fail", message)
        }
    }

    override fun onSearchNothing(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        binding.foodtypeRotateIv.visibility = View.GONE
        binding.foodtypeLoadingIv.visibility = View.GONE
        binding.foodtypeRotateIv.clearAnimation()
        foodtypeRVAdapter.noIngredients()
    }


}