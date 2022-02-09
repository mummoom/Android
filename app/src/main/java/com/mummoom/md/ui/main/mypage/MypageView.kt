package com.mummoom.md.ui.main.mypage

import com.mummoom.md.data.entities.User


interface MypageView {
    fun onMypageLoading()
    fun onMypageSuccess(user: User)
    fun onMypageFailure(code: Int, message: String)
}