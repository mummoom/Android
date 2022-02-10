package com.mummoom.md.data.remote.Post

import android.util.Log
import com.mummoom.md.ApplicationClass.Companion.retrofit
import com.mummoom.md.data.Post.SendPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PostService {

    private lateinit var defaultView : PostDefaultView
    private lateinit var postView: PostView
    private lateinit var getPostView : GetPostView
    private lateinit var getPostsView : GetPostsView

    // view를 초기에 세팅해주는 함수
    fun setDefaultView(newView: PostDefaultView)
    {
        defaultView = newView
    }

    fun setPostView(newView: PostView)
    {
        postView = newView
    }

    fun setGetPostView(newView: GetPostView)
    {
        getPostView = newView
    }

    fun setGetPostsView(newView: GetPostsView)
    {
        getPostsView = newView
    }

    // 여기부터는 api마다 함수를 만들어주면 됨

    // post 작성하는 API
    fun posting(newPost : SendPost)
    {
        val postService = retrofit.create(PostRetrofitInterface::class.java)

        postView.onPostLoading()

        postService.savePost(newPost).enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                val resp = response.body()!!

                when(resp.code)
                {
                    1000 -> postView.onPostSuccess(resp.data)
                    else -> postView.onPostFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                postView.onPostFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }

    // postIdx로 Post 받아오는 API
    fun getPost(postIdx : Int)
    {
        val getPostService = retrofit.create(PostRetrofitInterface::class.java)
        Log.d("postIdx_2", postIdx.toString())

        getPostService.getPostByPostIdx(postIdx).enqueue(object : Callback<GetPostResponse>{
            override fun onResponse(
                call: Call<GetPostResponse>,
                response: Response<GetPostResponse>
            ) {
                val resp = response.body()!!
                Log.d("post_success", resp.data.toString())
                when(resp.code)
                {
                    1000 -> getPostView.onGetPostSuccess(resp.data)
                    else -> getPostView.onGetPostFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<GetPostResponse>, t: Throwable) {
                getPostView.onGetPostFailure(400, "네트워크 오류가 발생했습니다.")
                Log.d("post_failure", "error", t)
            }

        })
    }

    // 모든 post 받아오는 API
    fun getPosts()
    {
        val getPostsService = retrofit.create(PostRetrofitInterface::class.java)

        // 로딩 걸기
//        getPostsView.onGetPostsLoading()

        getPostsService.getPosts().enqueue(object : Callback<GetPostsResponse>{
            override fun onResponse(
                call: Call<GetPostsResponse>,
                response: Response<GetPostsResponse>
            ) {
                val resp = response.body()!!

                when(resp.code)
                {
                    1000 -> getPostsView.onGetPostsSuccess(resp.data)
                    else -> getPostsView.onGetPostsFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<GetPostsResponse>, t: Throwable) {
                getPostsView.onGetPostsFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }



}