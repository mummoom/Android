package com.mummoom.md.ui.main.mypage

import com.mummoom.md.databinding.ActivityMylikedBinding
import com.mummoom.md.ui.BaseActivity

class MyLikedActivity : BaseActivity<ActivityMylikedBinding>(ActivityMylikedBinding::inflate) {

    override fun initAfterBinding() {
        binding.mylikedBackarrowBtn.setOnClickListener {
            finish()
        }

    }
}