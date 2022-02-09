package com.mummoom.md.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("email") val email: String,
    @SerializedName("imgUrl") val imgUrl : String,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("password") val password : String,
    @SerializedName("lastPassword") val lastPassword : String
) : Serializable
