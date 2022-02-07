package com.mummoom.md.ui.dogname

import android.content.Intent
import android.util.Log
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
            binding.dognameNextBtn -> nickname()

        }
    }

    private fun nickname() {
        if (binding.dognameNameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "강아지 이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        var dogname = binding.dognameNameEt.text.toString()
        dogname += ","
        val intent = Intent(this,DogbirthActivity::class.java)
        intent.putExtra("dogInfo",dogname)
        Log.d("DOGINFO_NAME",dogname)
        startActivity(intent)

    }

}