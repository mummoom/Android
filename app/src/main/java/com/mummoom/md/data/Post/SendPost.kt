package com.mummoom.md.data.Post

import com.google.gson.annotations.SerializedName

data class SendPost(
    @SerializedName("content") val content : String,
    @SerializedName("imgUrl") val imgUrl : String,
    @SerializedName("title") val title : String,
)
