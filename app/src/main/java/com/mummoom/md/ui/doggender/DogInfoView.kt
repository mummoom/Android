package com.mummoom.md.ui.doggender

interface DogInfoView {
    fun onDogInfoLoading()
    fun onDogInfoSuccess()
    fun onDogInfoFailure(code: Int, message: String)
}