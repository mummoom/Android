package com.mummoom.md.ui.main.mypage

import com.mummoom.md.data.entities.User


interface ChangeImgView {
    fun onChangeprofileLoading()
    fun onChangeprofileSuccess()
    fun onChangeprofileFailure(code: Int, message: String)
}