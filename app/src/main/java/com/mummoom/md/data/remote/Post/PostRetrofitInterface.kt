package com.mummoom.md.data.remote.Post

import com.mummoom.md.data.Post.SendPost
import retrofit2.Call
import retrofit2.http.*

interface PostRetrofitInterface {

    @POST("/post")
    fun savePost(
        @Body requestDto : SendPost
    ) : Call<PostResponse>

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