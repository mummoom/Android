package com.mummoom.md.data.remote.auth

import com.google.gson.annotations.SerializedName
data class Auth (@SerializedName("token") val token: String,
                 @SerializedName("dog_exist") val dog_exist: Boolean)

data class AuthResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Auth?
)