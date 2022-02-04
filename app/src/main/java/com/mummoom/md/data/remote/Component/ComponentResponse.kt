package com.mummoom.md.data.remote.Component

import com.google.gson.annotations.SerializedName

data class Ingredients(@SerializedName("component_idx") val component_idx: Int)

data class IngredientsResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Int,
)