package com.mummoom.md.ui.main.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mummoom.md.R
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.Ingredients.Search
import com.mummoom.md.data.remote.Ingredients.IngredientsService
import com.mummoom.md.data.remote.Ingredients.IngredientsView
import com.mummoom.md.data.remote.Ingredients.SearchService
import com.mummoom.md.data.remote.Ingredients.SearchView
import com.mummoom.md.databinding.ActivityFoodeatBinding
import com.mummoom.md.ui.BaseActivity


class FoodeatActivity : BaseActivity<ActivityFoodeatBinding>(ActivityFoodeatBinding::inflate), IngredientsView, SearchView {

    private lateinit var foodeatRVAdapter: FoodeatRVAdapter
    private var level: Int = -1
    private var ingredientName : String = ""

    override fun initAfterBinding() {

        binding.foodeatInfoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.foodeatBackIv.setOnClickListener {
            finish()
        }

        binding.foodeatSearchEt.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    foodSearch()
                    return true
                }
                return false
            }
        })

        binding.foodeatSearchIv.setOnClickListener{
            if(binding.foodeatSearchEt.length() <= 0){
                Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            }else{
                foodSearch()
            }
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

    private fun foodSearch(){
        ingredientName = binding.foodeatSearchEt.text.toString()

        val newSearch = Search(ingredientName)
        val searchService = SearchService()

        searchService.setSearchView(this)
        searchService.getInfoBySearch(newSearch)
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

    //ingredientsView
    override fun onIngredientsLoading() {
        binding.foodeatRotateIv.visibility = View.VISIBLE
        binding.foodeatLoadingIv.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        binding.foodeatRotateIv.startAnimation(animation)
    }

    override fun onIngredientsSuccess(ingredients: ArrayList<Ingredients>) {
        binding.foodeatRotateIv.visibility = View.GONE
        binding.foodeatLoadingIv.visibility = View.GONE
        binding.foodeatRotateIv.clearAnimation()
        foodeatRVAdapter.addIngredients(ingredients)
    }

    override fun onIngredientsFailure(code: Int, message: String) {
        binding.foodeatRotateIv.visibility = View.GONE
        binding.foodeatLoadingIv.visibility = View.GONE
        binding.foodeatRotateIv.clearAnimation()
        when(code){
            400 -> Log.d("fail", message)
        }
    }
    //searchView
    override fun onSearchLoading() {
        binding.foodeatRotateIv.visibility = View.VISIBLE
        binding.foodeatLoadingIv.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        binding.foodeatRotateIv.startAnimation(animation)
    }

    override fun onSearchSuccess(searchIngredients: ArrayList<Ingredients>) {
        binding.foodeatRotateIv.visibility = View.GONE
        binding.foodeatLoadingIv.visibility = View.GONE
        binding.foodeatRotateIv.clearAnimation()
        foodeatRVAdapter.addIngredients(searchIngredients)
    }

    override fun onSearchFailure(code: Int, message: String) {
        binding.foodeatRotateIv.visibility = View.GONE
        binding.foodeatLoadingIv.visibility = View.GONE
        binding.foodeatRotateIv.clearAnimation()
        when(code){
            400 -> Log.d("fail", message)
        }
    }

    override fun onSearchNothing(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        binding.foodeatRotateIv.visibility = View.GONE
        binding.foodeatLoadingIv.visibility = View.GONE
        binding.foodeatRotateIv.clearAnimation()
        foodeatRVAdapter.noIngredients()
    }
}