package com.mummoom.md.ui.signupCheck

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.mummoom.md.databinding.ActivitySignupBinding
import com.mummoom.md.databinding.ActivitySignupCheckBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogname.DognameActivity
import com.mummoom.md.ui.login.LoginActivity
import com.mummoom.md.ui.nickname.NicknameActivity

class SignUpCheckActivity : BaseActivity<ActivitySignupCheckBinding>(ActivitySignupCheckBinding::inflate), View.OnClickListener {

    override fun initAfterBinding() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivityWithClear(DognameActivity::class.java)
        }, 2000)
    }

    override fun onClick(v: View?) {
    }
}