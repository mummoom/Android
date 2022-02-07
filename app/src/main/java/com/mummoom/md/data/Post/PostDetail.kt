package com.mummoom.md.data.Post

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PostDetail(
    @SerializedName("comments") val comments : String,
    @SerializedName("content") val content : String,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("imgUrl") val imgUrl : String,
    @SerializedName("likecnt") val likecnt : Int,
    @SerializedName("title") val title : String,
    @SerializedName("userIdx") val userIdx : Int,
    @SerializedName("userImage") val userImage : String,
    @SerializedName("userName") val userName : String
){
    @SerializedName("postIdx") @PrimaryKey(autoGenerate = true) var postIdx : Int = 0
}
