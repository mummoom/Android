package com.mummoom.md.data.remote.User


import com.mummoom.md.data.entities.User
import com.mummoom.md.data.entities.WIthdrawUser
import retrofit2.Call
import retrofit2.http.*


interface UserRetrofitInterface {
    @GET("/api/users/me")
    fun getUserByIdx(): Call<UserResponse>

    @PUT("/api/users/pwd")
    fun changePwd(@Body user: User) : Call<PwdResponse>

    @PATCH("/api/users/mynickname")
    fun changeUsername(@Body user: User): Call<PwdResponse>

    @PATCH("/api/users/myimg")
    fun changeUserImg(
        @Body imgUrl : String): Call<PwdResponse>

    @HTTP(method = "DELETE", path = "/api/users/withdraw", hasBody = true)
    fun withdrawUser(@Body wIthdrawUser: WIthdrawUser): Call<PwdResponse>

    @HTTP(method = "DELETE", path = "/api/users/oauthwithdraw", hasBody = true)
    fun oauthwithdrawUser(): Call<PwdResponse>
}