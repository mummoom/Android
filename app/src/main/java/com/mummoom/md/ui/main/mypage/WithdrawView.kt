package com.mummoom.md.ui.main.mypage

import com.mummoom.md.data.entities.User


interface WithdrawView {
    fun onWithdrawLoading()
    fun onWithdrawSuccess()
    fun onWithdrawFailure(code: Int, message: String)
}