package com.mummoom.md.ui.main.mypage

import com.mummoom.md.databinding.ActivityAskBinding
import com.mummoom.md.ui.BaseActivity

class AskActivity : BaseActivity<ActivityAskBinding>(ActivityAskBinding::inflate) {
    override fun initAfterBinding() {
        binding.askBackarrowBtn.setOnClickListener{
            finish()
        }
    }

}