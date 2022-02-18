package com.mummoom.md.ui.main.mypage

import com.mummoom.md.data.entities.User


interface OauthWithdrawView {
    fun onOauthWithdrawLoading()
    fun onOauthWithdrawSuccess()
    fun onOauthWithdrawFailure(code: Int, message: String)
}