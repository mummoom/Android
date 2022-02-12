package com.mummoom.md.ui.main.mypage

import com.mummoom.md.databinding.ActivityUpdateBinding
import com.mummoom.md.ui.BaseActivity

class UpdateActivity : BaseActivity<ActivityUpdateBinding>(ActivityUpdateBinding::inflate) {
    override fun initAfterBinding() {
        binding.updateBackarrowBtn.setOnClickListener{
            finish()
        }
    }

}