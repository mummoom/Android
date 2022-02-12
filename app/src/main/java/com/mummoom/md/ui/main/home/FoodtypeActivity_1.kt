//package com.mummoom.md.ui.main.home
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.mummoom.md.data.Ingredients.Ingredients
//import com.mummoom.md.data.Ingredients.Search
//import com.mummoom.md.data.remote.Ingredients.IngredientsService
//import com.mummoom.md.data.remote.Ingredients.IngredientsView
//import com.mummoom.md.data.remote.Ingredients.SearchService
//import com.mummoom.md.data.remote.Ingredients.SearchView
//import com.mummoom.md.databinding.ActivityFoodtypeBinding
//import com.mummoom.md.ui.BaseActivity
//
//
//class FoodtypeActivity : BaseActivity<ActivityFoodtypeBinding>(ActivityFoodtypeBinding::inflate),IngredientsView,
//    SearchView {
//
//    private lateinit var foodtypeRVAdapter: FoodtypeRVAdapter
//    private lateinit var searchRVAdapter: SearchRVAdapter
//    private var categoryNum: Int = -1
//
//
//
//
//    override fun initAfterBinding() {
//
//        binding.foodtypeInfoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//
//        binding.foodtypeBackIv.setOnClickListener {
//            finish()
//        }
//
//        binding.foodtypeSearchIv.setOnClickListener {
//            if (binding.foodtypeSearchEt.text.isEmpty()) {
//                Toast.makeText(this, "검색어를 입력하세요", Toast.LENGTH_LONG).show()
//            } else{
//                val keyword = binding.foodtypeSearchEt.text.toString()
//                getFoodSearch(keyword)
//            }
//        }
//
//    }
//
//    private fun getFoodSearch(keyword:String){
//        Log.d("getfoodsearch", keyword)
//
//        val searchService = SearchService()
//        searchService.setSearchView(this)
//        searchService.getInfoBySearch(keyword)
//    }
//
//    private fun initRecyclerView(){
//        foodtypeRVAdapter = FoodtypeRVAdapter(this)
//        searchRVAdapter = SearchRVAdapter(this)
//        binding.foodtypeInfoRv.adapter = foodtypeRVAdapter
//        binding.foodtypeInfoRv.adapter = searchRVAdapter
//
//        foodtypeRVAdapter.setMyItemClickListener(object : FoodtypeRVAdapter.MyItemClickListener{
//
//            override fun onItemClick(ingredients: Ingredients) {
//                val intent = Intent(this@FoodtypeActivity, FoodinfoActivity::class.java)
//
//                intent.putExtra("ingredientInfo", ingredients)
//                startActivity(intent)
//            }
//
//
//        })
//
//        searchRVAdapter.setMyItemClickListener(object : SearchRVAdapter.MyItemClickListener{
//            override fun onSearchedItemClick(search: Search) {
//                val intent = Intent(this@FoodtypeActivity, FoodinfoActivity::class.java)
//
//                intent.putExtra("ingredientInfo", search)
//                startActivity(intent)
//            }
//
//        })
//    }
//
//
//    private fun getFoodtype(){
//
//        Log.d("categoryNum",categoryNum.toString())
//        val foodtypeService = IngredientsService()
//        foodtypeService.setIngredientsView(this)
//        foodtypeService.getInfoByCategory(categoryNum)
//
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        binding.foodtypeTitleTv.text = intent.getStringExtra("category")
//
//        if(intent.hasExtra("categoryNum")){
//            categoryNum = intent.getIntExtra("categoryNum",-1)
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        initRecyclerView()
//        getFoodtype()
//    }
//
////IngredientsView
//    override fun onIngredientsLoading() {
//        TODO("Not yet implemented")
//    }
//
//    override fun onIngredientsSuccess(ingredients: ArrayList<Ingredients>) {
//        foodtypeRVAdapter.addIngredients(ingredients)
//        Log.d("ingredients",ingredients.toString())
//    }
//
//    override fun onIngredientsFailure(code: Int, message: String) {
//        when(code){
//            400 -> Log.d("fail", message)
//        }
//    }
//
//    override fun onSearchLoading() {
//        TODO("Not yet implemented")
//    }
//
//    override fun onSearchSuccess(search: ArrayList<Search>) {
//        searchRVAdapter.addSearchFood(search)
//    }
//
//    override fun onSearchFailure(code: Int, message: String) {
//        when(code){
//            400 -> Log.d("fail", message)
//        }
//    }
//
//
//}