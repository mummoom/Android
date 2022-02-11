package com.mummoom.md.ui.main.mypage

import android.os.Handler
import android.os.Looper
import android.view.View
import com.mummoom.md.databinding.ActivityWithdrawalBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.login.LoginActivity

class WithdrawalActivity : BaseActivity<ActivityWithdrawalBinding>(ActivityWithdrawalBinding::inflate) {

    override fun initAfterBinding() {

            Handler(Looper.getMainLooper()).postDelayed({
                startActivityWithClear(LoginActivity::class.java)
            }, 2000)


    }


}