package com.mummoom.md.data.remote.Ingredients

import com.google.gson.annotations.SerializedName
import com.mummoom.md.data.Ingredients.Ingredients


data class SearchResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data : ArrayList<Ingredients>
)