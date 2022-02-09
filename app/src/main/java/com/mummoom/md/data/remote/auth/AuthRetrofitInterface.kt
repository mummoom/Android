package com.mummoom.md.data.remote.auth

import com.mummoom.md.data.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/api/users/signup")
    fun signUp(@Body user: User): Call<SignupResponse>

    @POST("/api/users/login")
    fun login(@Body user: User): Call<AuthResponse>

    @GET("/users/auto-login")
    fun autoLogin(): Call<AuthResponse>

    @GET("/api/users/me")
    fun getUserByIdx(@Body user: User): Call<AuthResponse>
}