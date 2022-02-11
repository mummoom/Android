package com.mummoom.md.data.remote.Post

import com.google.gson.annotations.SerializedName

data class LikeResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("data") val data : Boolean,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String
)