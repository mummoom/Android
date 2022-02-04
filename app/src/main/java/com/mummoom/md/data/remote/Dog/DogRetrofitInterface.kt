package com.mummoom.md.data.remote.Dog

import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface DogRetrofitInterface {
    @POST("/api/dog/save") //save
    fun dogInfo(@Body dog: Dog): Call<DogResponse>

    @GET("/api/dog/{dogIdx}") //get dog by Idx
    fun login(@Body user: User): Call<DogResponse>

//    @PATCH("/api/dog/{dodIdx}") // delete dog
//    fun autoLogin(): Call<DogResponse>
//
//    @PATCH("/api/dog/update/{dodIdx}") //update dog
//    fun autoLogin(): Call<DogResponse>
}