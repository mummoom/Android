package com.mummoom.md.ui.main.mypage

import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.entities.User


interface MypageView {
    fun onMypageLoading()
    fun onMypageSuccess(dogs :  ArrayList<Dog>)
    fun onMypageFailure(code: Int, message: String)
}