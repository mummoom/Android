package com.mummoom.md.ui.dogname

import android.view.View
import com.mummoom.md.databinding.ActivityDognameBinding
import com.mummoom.md.databinding.ActivityNicknameBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogbirth.DogbirthActivity

class DognameActivity : BaseActivity<ActivityDognameBinding>(ActivityDognameBinding::inflate), View.OnClickListener{
    override fun initAfterBinding() {
        binding.dognameNextBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.dognameNextBtn -> startActivityWithClear(DogbirthActivity::class.java)

        }
    }

}