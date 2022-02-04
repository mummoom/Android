package com.mummoom.md.ui.nickname

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.mummoom.md.databinding.ActivityNicknameBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogbirth.DogbirthActivity
import com.mummoom.md.ui.dogname.DognameActivity
import com.mummoom.md.ui.signupCheck.SignUpCheckActivity

class NicknameActivity : BaseActivity<ActivityNicknameBinding>(ActivityNicknameBinding::inflate) , View.OnClickListener {
    override fun initAfterBinding() {
        binding.nicknameNextBtn.setOnClickListener(this)


    }
    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {




        }
    }

//    private fun nickname() {
//        if (binding.nicknameNameEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//        val nickname = binding.nicknameNameEt.text.toString()+","
//        val intent = Intent(this,DogbirthActivity::class.java)
//        intent.putExtra("dogInfo",nickname)
//        startActivity(intent)
//
//    }

}