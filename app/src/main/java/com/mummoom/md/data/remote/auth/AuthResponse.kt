package com.mummoom.md.data.remote.auth

import com.google.gson.annotations.SerializedName

data class Auth(@SerializedName("jwt") val jwt: String)

data class AuthResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Int,
    @SerializedName("result") val result: Auth?
)