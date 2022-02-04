package com.mummoom.md.data.remote.Post

import com.mummoom.md.ApplicationClass.Companion.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PostService {

    private lateinit var postView : PostDefaultView
    private lateinit var getPostView : GetPostView
    private lateinit var getPostsView : GetPostsView

    // view를 초기에 세팅해주는 함수
    fun setPostView(newView: PostDefaultView)
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
    // post-controller : GET("/posts")
    fun getPosts()
    {
        val getPostsService = retrofit.create(PostRetrofitInterface::class.java)

        // 로딩 걸기
        getPostsView.onGetPostsLoading()

        getPostsService.getPosts().enqueue(object : Callback<GetPostsResponse>{
            override fun onResponse(
                call: Call<GetPostsResponse>,
                response: Response<GetPostsResponse>
            ) {
                val resp = response.body()!!

                when(resp.status)
                {
                    200 -> getPostsView.onGetPostsSuccess(resp.data)
                    else -> getPostsView.onGetPostsFailure(resp.status, resp.message)
                }
            }

            override fun onFailure(call: Call<GetPostsResponse>, t: Throwable) {
                getPostsView.onGetPostsFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }



}