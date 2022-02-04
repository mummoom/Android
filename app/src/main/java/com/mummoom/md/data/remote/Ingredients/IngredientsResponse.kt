package com.mummoom.md.data.remote.Ingredients

import com.google.gson.annotations.SerializedName

data class Ingredients(@SerializedName("ingredients_idx") val ingredients_idx: Int)

data class IngredientsResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Int,
)