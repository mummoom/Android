package com.mummoom.md.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mummoom.md.R
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.Ingredients.Search
import com.mummoom.md.data.remote.Ingredients.SearchService
import com.mummoom.md.databinding.ActivitySearchBinding
import com.mummoom.md.ui.BaseActivity

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate),com.mummoom.md.data.remote.Ingredients.SearchView{

    private lateinit var searchRVAdapter: SearchRVAdapter
    private var ingredientName : String = ""

    override fun initAfterBinding() {
        binding.searchInfoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.searchBackIv.setOnClickListener {
            finish()
        }

    }

    private fun initRecyclerView(){
        searchRVAdapter = SearchRVAdapter(this)
        binding.searchInfoRv.adapter = searchRVAdapter

        searchRVAdapter.setMyItemClickListener(object : SearchRVAdapter.MyItemClickListener{

            override fun onItemClick(ingredients: Ingredients){
                val intent = Intent(this@SearchActivity, FoodinfoActivity::class.java)
                intent.putExtra("ingredientInfo", ingredients)
                startActivity(intent)
            }
        })
    }

   fun getFoodSearch(){
        val newSearch = Search(ingredientName)
        val searchService = SearchService()

        searchService.setSearchView(this)
        searchService.getInfoBySearch(newSearch)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra("ingredientName")){
            ingredientName = intent.getStringExtra("ingredientName").toString()
        }

    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        getFoodSearch()
    }

    override fun onSearchLoading() {
        binding.searchRotateIv.visibility = View.VISIBLE
        binding.searchLoadingIv.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        binding.searchRotateIv.startAnimation(animation)
    }

    override fun onSearchSuccess(searchIngredients: ArrayList<Ingredients>) {
        binding.searchRotateIv.visibility = View.GONE
        binding.searchLoadingIv.visibility = View.GONE
        binding.searchRotateIv.clearAnimation()
        searchRVAdapter.addIngredients(searchIngredients)
    }

    override fun onSearchFailure(code: Int, message: String) {
        binding.searchRotateIv.visibility = View.GONE
        binding.searchLoadingIv.visibility = View.GONE
        binding.searchRotateIv.clearAnimation()
        when(code){
            400 -> Log.d("fail", message)
        }
    }

    override fun onSearchNothing(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        binding.searchRotateIv.visibility = View.GONE
        binding.searchLoadingIv.visibility = View.GONE
        binding.searchRotateIv.clearAnimation()
        searchRVAdapter.noIngredients()
    }

}