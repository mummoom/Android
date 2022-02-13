package com.mummoom.md.data.Post

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("commentIdx") var commentIdx : Int,
    @SerializedName("userIdx") var userIdx : Int,
    @SerializedName("nickName") var nickName : String,
    @SerializedName("content") var content : String,
    @SerializedName("nestedComments") var nestedComments : ArrayList<Comment>,
    @SerializedName("createdAt") var createAt : String
)
