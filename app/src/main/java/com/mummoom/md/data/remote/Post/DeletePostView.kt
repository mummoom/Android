package com.mummoom.md.data.remote.Post

interface DeletePostView {
    fun onDeleteLoading()
    fun onDeleteSuccess()
    fun onDeleteFailure(code : Int, message : String)
}