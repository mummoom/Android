package com.mummoom.md.data.remote.Ingredients

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IngredientsRetrofitInterface {

    @GET("/api/ingredients/{categoryNum}")
    fun getIngredientsCategory(
        @Path("categoryNum")categoryNum: Int
    ) : Call<IngredientsResponse>

    @GET("/api/simple/ingredients/{level}")
    fun getIngredientsSimple(
        @Path("level")level: Int
    ) : Call<IngredientsResponse>
}