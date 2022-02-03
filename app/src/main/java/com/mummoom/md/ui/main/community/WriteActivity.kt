package com.mummoom.md.ui.main.community

import com.mummoom.md.databinding.ActivityWriteBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.main.mypage.ChangeImageCustomDialog

class WriteActivity : BaseActivity<ActivityWriteBinding>(ActivityWriteBinding::inflate) {

    override fun initAfterBinding() {

        val plusImage = ChangeImageCustomDialog(this)

        // 뒤로가기 눌렀을 때
        binding.writeArrowIv.setOnClickListener {
            finish()
        }

        // 이미지 추가 버튼 눌렀을 때
        binding.writeAddImgIv.setOnClickListener {
            plusImage.MyDig()
        }
    }

}