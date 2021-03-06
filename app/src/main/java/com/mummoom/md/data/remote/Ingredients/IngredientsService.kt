package com.mummoom.md.data.remote.Ingredients

import android.content.Intent
import android.util.Log
import com.mummoom.md.ApplicationClass.Companion.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientsService {

    private lateinit var ingredientsView: IngredientsView

    fun setIngredientsView(newView : IngredientsView)
    {
        ingredientsView = newView
    }


    fun getInfoBySimple(level: Int)
    {
        val getInfoBySimpleService = retrofit.create(IngredientsRetrofitInterface::class.java)



//        ingredientsView.onIngredientsLoading()



        getInfoBySimpleService.getIngredientsSimple(level).enqueue(object : Callback<IngredientsResponse>{
            override fun onResponse(
                call: Call<IngredientsResponse>,
                response: Response<IngredientsResponse>
            ) {
                val resp = response.body()!!

                when(resp.code)
                {
                    1000 -> ingredientsView.onIngredientsSuccess(resp.data)
                    else -> ingredientsView.onIngredientsFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<IngredientsResponse>, t: Throwable) {
                ingredientsView.onIngredientsFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }


    fun getInfoByCategory(categoryNum: Int)
    {
        val getInfoByCategoryService = retrofit.create(IngredientsRetrofitInterface::class.java)



//        ingredientsView.onIngredientsLoading()



        getInfoByCategoryService.getIngredientsCategory(categoryNum).enqueue(object : Callback<IngredientsResponse>{
            override fun onResponse(
                call: Call<IngredientsResponse>,
                response: Response<IngredientsResponse>
            ) {
                val resp = response.body()!!

                when(resp.code)
                {
                    1000 -> ingredientsView.onIngredientsSuccess(resp.data)
                    else -> ingredientsView.onIngredientsFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<IngredientsResponse>, t: Throwable) {
                ingredientsView.onIngredientsFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }
}