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
    fun getPostByPostIdx(
        @Path("postIdx") postIdx : Int
    ) : Call<GetPostResponse>

    @DELETE("/post/{postIdx}")
    fun deletePost(
        @Path("postIdx") postIdx : Int
    ) : Call<DefaultPostResponse>

    @PATCH("/post/{postIdx}")
    fun updatePost() : Call<DefaultPostResponse>

    @GET("/post/findMyLikes")
    fun getLikedPost() : Call<GetPostsResponse>

    @GET("/post/findMyPosts")
    fun getMyPost() : Call<GetPostsResponse>

    @GET("/posts")
    fun getPosts() : Call<GetPostsResponse>

    @POST("/like/{postIdx}")
    fun setLike(
        @Path("postIdx") postIdx : Int
    ) : Call<LikeResponse>
}