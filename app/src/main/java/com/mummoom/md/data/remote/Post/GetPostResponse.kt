package com.mummoom.md.data.remote.Post

import com.google.gson.annotations.SerializedName
import com.mummoom.md.data.Post.PostDetail

// Post 한 개 받을 때
data class GetPostResponse(
    @SerializedName("code") var code : Int,
    @SerializedName("data") var data : PostDetail,
    @SerializedName("isSuccess") var isSuccess : Boolean,
    @SerializedName("message") var message : String
)