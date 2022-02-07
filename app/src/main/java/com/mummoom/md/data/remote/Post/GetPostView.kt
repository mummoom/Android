package com.mummoom.md.data.remote.Post

import com.mummoom.md.data.Post.PostDetail

interface GetPostView {
    fun onGetPostLoading()
    fun onGetPostSuccess(post : PostDetail)
    fun onGetPostFailure(code : Int, message : String)
}