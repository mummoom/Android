package com.mummoom.md.data.remote.User


import retrofit2.Call
import retrofit2.http.GET


interface UserRetrofitInterface {
    @GET("/api/users/me")
    fun getUserByIdx(): Call<UserResponse>
}