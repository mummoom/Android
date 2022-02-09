package com.mummoom.md.data.Post

import com.google.gson.annotations.SerializedName

data class SendPost(
    @SerializedName("content") var content : String,
    @SerializedName("imgUrl") var imgUrl : String,
    @SerializedName("title") var title : String,
)
