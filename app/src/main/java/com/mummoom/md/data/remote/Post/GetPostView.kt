package com.mummoom.md.data.remote.Post

import com.mummoom.md.data.Post.Post

interface GetPostView {
    fun onGetPostLoading()
    fun onGetPostSuccess(post : Post)
    fun onGetPostFailure(code : Int, message : String)
}