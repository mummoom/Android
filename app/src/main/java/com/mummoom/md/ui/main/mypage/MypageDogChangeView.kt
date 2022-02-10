package com.mummoom.md.ui.main.mypage

import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.entities.User


interface MypageDogChangeView {
    fun onMypageDogchangeLoading()
    fun onMypageDogchangeSuccess()
    fun onMypageDogchangeFailure(code: Int, message: String)
}