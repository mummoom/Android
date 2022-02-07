package com.mummoom.md.data.remote.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: String
)