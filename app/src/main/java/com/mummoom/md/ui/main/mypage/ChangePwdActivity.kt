package com.mummoom.md.ui.main.mypage

import android.view.View
import android.widget.Toast
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.User.UserService
import com.mummoom.md.databinding.ActivityChangepwBinding
import com.mummoom.md.ui.BaseActivity

class ChangePwdActivity: BaseActivity<ActivityChangepwBinding>(ActivityChangepwBinding::inflate), View.OnClickListener,ChangepwView{

    override fun initAfterBinding() {

        // 뒤로가기 버튼 눌렀을 때
        binding.changepwBackarrowBtn.setOnClickListener {
            finish()
        }
        binding.changepwOkayBtn.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.changepwOkayBtn -> changePw()

        }
    }

    private fun changePw() {
        if(binding.changepwNewpwEt.text.toString()!=binding.changepwNewpwcheckEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if(binding.changepwNewpwEt.text.toString().isEmpty()) {
            Toast.makeText(this, "새로운 비밀번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        UserService.changePwd(this,getPwd())
    }

    private fun getPwd(): User {
        val newpassword:String = binding.changepwNewpwEt.text.toString()
        val lastPassword : String = binding.changepwOldpwEt.text.toString()

        return User("","","","",lastPassword,newpassword)

    }

    override fun onChangepwLoading() {

    }

    override fun onChangepwSuccess() {
        Toast.makeText(this, "비밀번호가 성공적으로 변경되었습니다.", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onChangepwFailure(code: Int, message: String) {

    }
}