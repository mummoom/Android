package com.mummoom.md.data.remote.Post

import com.mummoom.md.data.Post.Post

interface GetPostsView {
    fun onGetPostsLoading()
    fun onGetPostsSuccess(posts : ArrayList<Post>)
    fun onGetPostsFailure(code : Int, message : String)
}