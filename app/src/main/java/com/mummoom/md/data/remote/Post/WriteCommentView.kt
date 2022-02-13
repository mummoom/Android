package com.mummoom.md.data.remote.Post

interface WriteCommentView {
    fun onWriteCommentLoading()
    fun onWriteCommentSuccess()
    fun onWriteCommentFailure(code : Int, message : String)
}