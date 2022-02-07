package com.mummoom.md.ui.login



interface LoginView {
    fun onLoginLoading()
    fun onLoginSuccess(jwt:String)
    fun onLoginFailure(code: Int, message: String)
}