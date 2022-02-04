package com.mummoom.md.data.entities

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") val email: String,
    @SerializedName("imgUrl") val imgUrl : String ,
    @SerializedName("nickname") val name: String,
    @SerializedName("password") val password : String
)
