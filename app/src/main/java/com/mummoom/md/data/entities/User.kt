package com.mummoom.md.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("email") val email: String,
    @SerializedName("imgUrl") var imgUrl : String,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("password") val password : String,
    @SerializedName("lastPassword") val lastPassword : String,
    @SerializedName("newPassword") val newPassword : String
) : Serializable
