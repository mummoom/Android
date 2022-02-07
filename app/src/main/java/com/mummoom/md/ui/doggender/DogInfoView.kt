package com.mummoom.md.ui.doggender

import com.mummoom.md.data.entities.Dog


interface DogInfoView {
    fun onDogInfoLoading()
    fun onDogInfoSuccess(dogIdx: Dog)
    fun onDogInfoFailure(code: Int, message: String)
}