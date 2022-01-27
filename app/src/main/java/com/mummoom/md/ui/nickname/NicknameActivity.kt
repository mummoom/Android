package com.mummoom.md.ui.nickname

import android.view.View
import com.mummoom.md.databinding.ActivityNicknameBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogname.DognameActivity
import com.mummoom.md.ui.signupCheck.SignUpCheckActivity

class NicknameActivity : BaseActivity<ActivityNicknameBinding>(ActivityNicknameBinding::inflate) , View.OnClickListener {
    override fun initAfterBinding() {
        binding.nicknameNextBtn.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {

            binding.nicknameNextBtn -> startActivityWithClear(DognameActivity::class.java)

        }
    }

}