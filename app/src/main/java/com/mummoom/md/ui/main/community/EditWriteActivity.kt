package com.mummoom.md.ui.main.community

import com.mummoom.md.databinding.ActivityEditWriteBinding
import com.mummoom.md.ui.BaseActivity

class EditWriteActivity : BaseActivity<ActivityEditWriteBinding>(ActivityEditWriteBinding::inflate) {
    override fun initAfterBinding() {
        binding.editArrowIv.setOnClickListener{
            finish()
        }
    }
}