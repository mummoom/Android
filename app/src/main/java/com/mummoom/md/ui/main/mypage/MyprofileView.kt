package com.mummoom.md.ui.main.mypage

import com.mummoom.md.data.entities.User


interface MyprofileView {
    fun onMyprofileLoading()
    fun onMyprofileSuccess(user: User)
    fun onMyprofileFailure(code: Int, message: String)
}