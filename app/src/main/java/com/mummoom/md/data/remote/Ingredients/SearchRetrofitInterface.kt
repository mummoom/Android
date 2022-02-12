package com.mummoom.md.data.remote.Ingredients

import com.mummoom.md.data.Ingredients.Search
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchRetrofitInterface {

    @POST("/api/search")
    fun searchFood(
        @Body searchReqDto : Search
    ) : Call<SearchResponse>
}