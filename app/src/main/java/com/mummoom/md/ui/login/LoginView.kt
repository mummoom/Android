package com.mummoom.md.ui.login

import com.mummoom.md.data.remote.auth.Auth


interface LoginView {
    fun onLoginLoading()
    fun onLoginSuccess(auth : Auth)
    fun onLoginFailure(code: Int, message: String)
}