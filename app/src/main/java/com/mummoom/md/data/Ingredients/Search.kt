package com.mummoom.md.data.Ingredients

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("ingredientName") var ingredientName : String
)
