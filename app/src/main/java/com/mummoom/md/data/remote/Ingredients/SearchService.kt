package com.mummoom.md.data.remote.Ingredients

import android.util.Log
import com.mummoom.md.ApplicationClass.Companion.retrofit
import com.mummoom.md.data.Ingredients.Search
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService {

    private lateinit var searchView: SearchView

    fun setSearchView(newView: SearchView){
        searchView = newView
    }

    fun getInfoBySearch(newSearch : Search) {

        val searchService = retrofit.create(SearchRetrofitInterface::class.java)

        searchView.onSearchLoading()

        searchService.searchFood(newSearch).enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>)
            {
                val resp = response.body()!!

                when(resp.code){
                    1000 -> searchView.onSearchSuccess(resp.data)
                    4001 -> searchView.onSearchNothing(resp.message)
                    else -> searchView.onSearchFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                searchView.onSearchFailure(400,"네트워크 오류가 발생했습니다.")
            }

        })
    }
}