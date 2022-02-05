package com.mummoom.md.data.remote.Ingredients

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IngredientsRetrofitInterface {

    @GET("/api/ingredients/{categoryNum}")
    fun getIngredientsCategory() : Call<IngredientsResponse>

    @GET("/api/simple/ingredients/{level}")
    fun getSimpleInfo() : Call<IngredientsResponse>
}