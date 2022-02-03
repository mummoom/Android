package com.mummoom.md.ui.main.community

import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.mummoom.md.databinding.ActivityWriteBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.main.mypage.ChangeImageCustomDialog

class WriteActivity : BaseActivity<ActivityWriteBinding>(ActivityWriteBinding::inflate) {

//    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent())
//    {
////        it -> // 받아온 url로 writeActivity 화면을 change 해주기?
//    }

    override fun initAfterBinding() {

        val plusImage = PlusImageCustomDialog(this)

        // 앱에서 앨범 접근을 허용할지 선택하는 메세지 (한번 허용하면 앱 설치되어있는 동안 안 뜸.)
//        ActivityCompat.requestPermissions(this,
//            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

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