package com.mummoom.md.ui.login

import com.mummoom.md.data.remote.auth.Auth


interface GoogleLoginView {
    fun onGoogleLoginLoading()
    fun onGoogleLoginSuccess(auth : Auth)
    fun onGoogleLoginFailure(code: Int, message: String)
}