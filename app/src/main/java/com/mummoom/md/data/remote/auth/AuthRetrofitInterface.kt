package com.mummoom.md.data.remote.auth

import com.mummoom.md.data.entities.User
import retrofit2.Call
import retrofit2.http.*

interface AuthRetrofitInterface {
    @POST("/api/users/signup")
    fun signUp(@Body user: User): Call<SignupResponse>

    @POST("/api/users/login")
    fun login(@Body user: User): Call<AuthResponse>

    @GET("/api/users/validtoken")
    fun autoLogin(): Call<SignupResponse>

    @GET("/api/users/login/google")
    fun googleLogin(
        @Query("accessToken")accessToken:String
    ): Call<AuthResponse>

}