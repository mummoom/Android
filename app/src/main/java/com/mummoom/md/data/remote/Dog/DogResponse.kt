package com.mummoom.md.data.remote.Dog

import com.google.gson.annotations.SerializedName
import com.mummoom.md.data.remote.auth.Auth


data class DogResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val result: Auth?
)