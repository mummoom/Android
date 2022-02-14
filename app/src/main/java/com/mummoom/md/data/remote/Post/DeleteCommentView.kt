package com.mummoom.md.data.remote.Post

interface DeleteCommentView {
    fun onDeleteCommentLoading()
    fun onDeleteCommentSuccess()
    fun onDeleteCommentFailure(code : Int, message : String)
}