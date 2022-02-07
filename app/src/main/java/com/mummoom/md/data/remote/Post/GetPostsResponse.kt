package com.mummoom.md.data.remote.Post

import com.google.gson.annotations.SerializedName
import com.mummoom.md.data.Post.Post

// 모든 Post 받을 때
data class GetPostsResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("data") val data : ArrayList<Post>,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String
)