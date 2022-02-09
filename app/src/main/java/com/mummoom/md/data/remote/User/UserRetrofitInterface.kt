package com.mummoom.md.data.remote.User


import com.mummoom.md.data.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT


interface UserRetrofitInterface {
    @GET("/api/users/me")
    fun getUserByIdx(): Call<UserResponse>

    @PUT("/api/users/pwd")
    fun changePwd(@Body user: User) : Call<PwdResponse>
}