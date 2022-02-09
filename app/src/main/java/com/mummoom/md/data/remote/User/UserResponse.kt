package com.mummoom.md.data.remote.User

import com.google.gson.annotations.SerializedName
import com.mummoom.md.data.entities.User


data class UserResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: User?
)