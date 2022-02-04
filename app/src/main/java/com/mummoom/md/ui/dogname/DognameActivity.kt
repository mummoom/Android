package com.mummoom.md.ui.dogname

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.mummoom.md.databinding.ActivityDognameBinding
import com.mummoom.md.databinding.ActivityNicknameBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogbirth.DogbirthActivity
import com.mummoom.md.ui.nickname.NicknameActivity

class DognameActivity : BaseActivity<ActivityDognameBinding>(ActivityDognameBinding::inflate), View.OnClickListener{
    override fun initAfterBinding() {
        binding.dognameNextBtn.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.dognameNextBtn -> startActivityWithClear(DogbirthActivity::class.java)

        }
    }

    private fun nickname() {
        if (binding.dognameNameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        val dogname = binding.dognameNameEt.text.toString()+","
        val intent = Intent(this,DogbirthActivity::class.java)
        intent.putExtra("dogInfo",dogname)
        startActivity(intent)

    }

}