package com.mummoom.md.data.Post

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("content") var content : String,
    @SerializedName("imgUrl") var imgUrl : String,
    @SerializedName("postIdx") var postIdx : Int,
    @SerializedName("title") var title : String,
    @SerializedName("userIdx") var userIdx : Int
)
