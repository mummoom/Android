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

    @POST("/report/{postIdx}")
    fun reportPost(
        @Path("postIdx") postIdx : Int,
        @Body requestDto : String
    ) : Call<PostResponse>

    @POST("/comment/{postIdx}")
    fun writeComment(
        @Path("postIdx") postIdx : Int,
        @Body requestDto : String
    ) : Call<PostResponse>

    @DELETE("/comment/{commentIdx}")
    fun deleteComment(
        @Path("commentIdx") commentIdx : Int,
    ) : Call<DefaultPostResponse>
}