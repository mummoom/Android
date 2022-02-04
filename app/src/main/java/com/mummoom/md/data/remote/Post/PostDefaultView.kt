package com.mummoom.md.data.remote.Post

interface PostDefaultView {
    fun onPostLoading()
    fun onPostSuccess(code : Int)
    fun onPostFailure(code : Int, message : String)
}