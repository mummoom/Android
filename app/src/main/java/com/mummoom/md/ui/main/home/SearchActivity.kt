//package com.mummoom.md.ui.main.home
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.SearchView
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.mummoom.md.data.Ingredients.Ingredients
//import com.mummoom.md.databinding.ActivitySearchBinding
//import com.mummoom.md.ui.BaseActivity
//
//class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate),SearchView{
//
//    private lateinit var searchRVAdapter: SearchRVAdapter
//    private var ingredientName : String = ""
//
//    override fun initAfterBinding() {
//        binding.searchInfoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//
//        binding.searchBackIv.setOnClickListener {
//            finish()
//        }
//
//    }
//
//    private fun initRecyclerView(){
//        searchRVAdapter = SearchRVAdapter(this)
//        binding.searchInfoRv.adapter = searchRVAdapter
//
//        searchRVAdapter.setMyItemClickListener(object : SearchRVAdapter.MyItemClickListener{
//
//            override fun onItemClick(ingredients: Ingredients){
//                val intent = Intent(this@SearchActivity, FoodinfoActivity::class.java)
//                intent.putExtra("ingredientInfo", ingredients)
//                startActivity(intent)
//            }
//        })
//    }
//
//    private fun getFoodSearch(){
//        //프래그먼트에서 준 값 받아서 ingredientName에 넣기
//
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        initRecyclerView()
//        getFoodSearch()
//    }
//
//}