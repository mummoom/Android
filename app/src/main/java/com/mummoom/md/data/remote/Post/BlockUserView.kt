package com.mummoom.md.data.remote.Post

interface BlockUserView {
    fun onBlockUserLoading()
    fun onBlockUserSuccess()
    fun onBlockUserFailure(code : Int, message : String)
}