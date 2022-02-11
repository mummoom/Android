package com.mummoom.md.data.remote.Post

interface LikeView {
    fun onLikeLoading()
    fun onLikeSuccess(isLike : Boolean)
    fun onLikeFailure(code:  Int, message : String)
}