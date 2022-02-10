package com.mummoom.md.data.remote.Dog

import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.entities.User
import retrofit2.Call
import retrofit2.http.*

interface DogRetrofitInterface {
    @POST("/api/dog/save") //save
    fun dogInfo(@Body dog: Dog): Call<DogResponse>

    @GET("/api/dog/list") //get dog by Idx
    fun getDoglist(): Call<DogListResponse>

    @PATCH("/api/dog/update/{dogIdx}") //update dog
    fun changeDog(
        @Path ("dogIdx")dogIdx:Int,
        @Body dog: Dog): Call<ChangeDogResponse>



//    @PATCH("/api/dog/{dodIdx}") // delete dog
//    fun autoLogin(): Call<DogResponse>
//

}