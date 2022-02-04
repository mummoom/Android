package com.mummoom.md.data.remote.Post

import com.google.gson.annotations.SerializedName
import com.mummoom.md.data.Post.Post

// Post 한 개 받을 때
data class GetPostResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("data") val data : Post,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("status") val status : Int
)