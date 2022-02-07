package com.mummoom.md.data.remote.Post

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface PostRetrofitInterface {

    @POST("/post")
    fun savePost() : Call<DefaultPostResponse>

    @GET("/post/{postIdx}")
    fun getPostByPostIdx() : Call<GetPostResponse>

    @DELETE("/post/{postIdx}")
    fun deletePost() : Call<DefaultPostResponse>

    @PATCH("/post/{postIdx}")
    fun updatePost() : Call<DefaultPostResponse>

    @GET("/post/findMyLikes")
    fun getLikedPost() : Call<GetPostsResponse>

    @GET("/post/findMypost")
    fun getMyPost() : Call<GetPostsResponse>

    @GET("/posts")
    fun getPosts() : Call<GetPostsResponse>
}