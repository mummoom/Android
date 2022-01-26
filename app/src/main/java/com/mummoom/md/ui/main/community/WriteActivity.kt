package com.mummoom.md.ui.main.community

import com.mummoom.md.databinding.ActivityWriteBinding
import com.mummoom.md.ui.BaseActivity

class WriteActivity : BaseActivity<ActivityWriteBinding>(ActivityWriteBinding::inflate) {

    override fun initAfterBinding() {

        binding.writeArrowIv.setOnClickListener {
            finish()
        }
    }

}