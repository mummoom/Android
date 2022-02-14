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
    private lateinit var setLikeView : LikeView
    private lateinit var deletePostView : DeletePostView
    private lateinit var reportPostView : ReportPostView
    private lateinit var writeCommentView : WriteCommentView
    private lateinit var deleteCommentView : DeleteCommentView

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

    fun setLikeView(newView: LikeView)
    {
        setLikeView = newView
    }

    fun setDeleteView(newView: DeletePostView)
    {
        deletePostView = newView
    }

    fun setReportView(newView: ReportPostView)
    {
        reportPostView = newView
    }

    fun setWriteCommentView(newView: WriteCommentView)
    {
        writeCommentView = newView
    }

    fun setDeleteCommentView(newView: DeleteCommentView)
    {
        deleteCommentView = newView
    }

    // 여기부터는 api마다 함수를 만들어주면 됨

    // comment 삭제하는 API
    fun deleteComment(commentIdx : Int)
    {
        val deleteCommentService = retrofit.create(PostRetrofitInterface::class.java)

        deleteCommentView.onDeleteCommentLoading()

        deleteCommentService.deleteComment(commentIdx).enqueue(object : Callback<DefaultPostResponse>{
            override fun onResponse(
                call: Call<DefaultPostResponse>,
                response: Response<DefaultPostResponse>
            ) {
                val resp = response.body()!!

                when(resp.code)
                {
                    1000 -> deleteCommentView.onDeleteCommentSuccess()
                    else -> deleteCommentView.onDeleteCommentFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<DefaultPostResponse>, t: Throwable) {
                deleteCommentView.onDeleteCommentFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }

    // comment 작성하는 API
    fun writeComment(postIdx: Int, content: String)
    {
        val writeCommentService = retrofit.create(PostRetrofitInterface::class.java)

        writeCommentView.onWriteCommentLoading()

        writeCommentService.writeComment(postIdx, content).enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                val resp = response.body()!!

                when(resp.code)
                {
                    1000 -> writeCommentView.onWriteCommentSuccess()
                    else -> writeCommentView.onWriteCommentFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                writeCommentView.onWriteCommentFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }

    // Post를 신고하는 API
    fun reportPost(postIdx: Int, reason: String)
    {
        val reportPostService = retrofit.create(PostRetrofitInterface::class.java)

        reportPostService.reportPost(postIdx, reason).enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                val resp = response.body()!!

                when(resp.code)
                {
                    1000 -> reportPostView.onReportPostSuccess()
                    else -> reportPostView.onReportPostFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                reportPostView.onReportPostFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }

    // Post를 delete하는 API
    fun deletePost(postIdx: Int)
    {
        val deletePostService = retrofit.create(PostRetrofitInterface::class.java)

        deletePostService.deletePost(postIdx).enqueue(object : Callback<DefaultPostResponse>{
            override fun onResponse(
                call: Call<DefaultPostResponse>,
                response: Response<DefaultPostResponse>
            ) {
                val resp = response.body()!!

                when(resp.code)
                {
                    1000 -> deletePostView.onDeleteSuccess()
                    else -> deletePostView.onDeleteFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<DefaultPostResponse>, t: Throwable) {
                deletePostView.onDeleteFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }

    // 내가 쓴 post를 받아오는 API
    fun getMyPost()
    {
        val getMyPostService = retrofit.create(PostRetrofitInterface::class.java)

        getMyPostService.getMyPost().enqueue(object : Callback<GetPostsResponse>{
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

    // liked post 받아오는 API
    fun getLiked()
    {
        val getLikedService = retrofit.create(PostRetrofitInterface::class.java)

        getLikedService.getLikedPost().enqueue(object : Callback<GetPostsResponse>{
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


    // post에 좋아요 설정하는 API
    fun setLike(postIdx: Int)
    {
        val likeService = retrofit.create(PostRetrofitInterface::class.java)

//        setLikeView.onLikeLoading()

        likeService.setLike(postIdx).enqueue(object : Callback<LikeResponse>{
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                val resp = response.body()!!

                when(resp.code)
                {
                    1000 -> setLikeView.onLikeSuccess(resp.data)
                    else -> setLikeView.onLikeFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                setLikeView.onLikeFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })
    }


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