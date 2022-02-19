package com.mummoom.md.data.remote.Dog

interface DeleteDogView {
    fun onDeleteDogLoading()
    fun onDeleteDogSuccess()
    fun onDeleteDogFailure(code : Int, message : String)
}