package com.mummoom.md.data.remote.Post

interface PostView {
    fun onPostLoading()
    fun onPostSuccess(data : Int)
    fun onPostFailure(code : Int, message : String)
}