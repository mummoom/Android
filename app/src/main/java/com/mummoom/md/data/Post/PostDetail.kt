package com.mummoom.md.data.Post

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PostDetail(
    @SerializedName("comments") var comments : ArrayList<Comment>,
    @SerializedName("content") var content : String,
    @SerializedName("createdAt") var createdAt : String,
    @SerializedName("imgUrl") var imgUrl : String,
    @SerializedName("like") var like : Boolean,
    @SerializedName("likecnt") var likecnt : Int,
    @SerializedName("postIdx") var postIdx : Int,
    @SerializedName("title") var title : String,
    @SerializedName("userIdx") var userIdx : Int,
    @SerializedName("userImage") var userImage : String,
    @SerializedName("userName") var userName : String
)
