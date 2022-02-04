package com.mummoom.md.data.remote.Post

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface PostRetrofitInterface {

    @GET("/post/{postIdx}")
    fun getPostByPostIdx() : Call<GetPostResponse>

    @DELETE("/post/{postIdx}")
    fun deletePost() : Call<DefaultPostResponse>

    @PATCH("/post/{postIdx}")
    fun updatePost() : Call<DefaultPostResponse>

    @POST("/post/{userIdx}")
    fun savePost() : Call<DefaultPostResponse>

    @GET("/posts")
    fun getPosts() : Call<GetPostsResponse>
}