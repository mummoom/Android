package com.mummoom.md.data.remote.Dog

import com.google.gson.annotations.SerializedName
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.entities.Dog


data class DogResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: Dog?,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
)