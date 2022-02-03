package com.mummoom.md.ui.main.mypage

import com.mummoom.md.databinding.ActivityChangepwBinding
import com.mummoom.md.databinding.ActivityIllustrationBinding
import com.mummoom.md.ui.BaseActivity

class IllustrationActivity : BaseActivity<ActivityIllustrationBinding>(ActivityIllustrationBinding::inflate) {

    override fun initAfterBinding() {

        // 뒤로가기 버튼 눌렀을 때
        binding.illustrationBackarrowBtn.setOnClickListener {
            finish()
        }
    }
}