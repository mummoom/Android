package com.mummoom.md.ui.main.mypage

import com.mummoom.md.data.entities.User


interface ChangenameView {
    fun onChangenameLoading()
    fun onChangenameSuccess()
    fun onChangenameFailure(code: Int, message: String)
}