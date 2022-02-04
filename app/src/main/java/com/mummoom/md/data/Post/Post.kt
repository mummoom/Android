package com.mummoom.md.data.Post

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("content") val content : String,
    @SerializedName("imgUrl") val imgUrl : String,
    @SerializedName("title") val title : String,
    @SerializedName("userIdx") val userIdx : Int
){
    @PrimaryKey(autoGenerate = true) var postIdx : Int = 0
}
