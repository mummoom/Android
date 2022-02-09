package com.mummoom.md.ui.main.mypage

import com.mummoom.md.data.entities.User


interface ChangepwView {
    fun onChangepwLoading()
    fun onChangepwSuccess()
    fun onChangepwFailure(code: Int, message: String)
}