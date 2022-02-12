package com.mummoom.md.ui.main.mypage

import com.mummoom.md.databinding.ActivityAppinfoBinding
import com.mummoom.md.ui.BaseActivity

class AppinfoActivity : BaseActivity<ActivityAppinfoBinding>(ActivityAppinfoBinding::inflate) {
    override fun initAfterBinding() {
        binding.appinfoBackarrowBtn.setOnClickListener{
            finish()
        }
    }

}