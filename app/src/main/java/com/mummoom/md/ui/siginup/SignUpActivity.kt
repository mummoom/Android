package com.mummoom.md.ui.siginup

import android.view.View
import android.widget.Toast
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.auth.AuthService
import com.mummoom.md.databinding.ActivitySignupBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.login.LoginActivity
import com.mummoom.md.ui.signupCheck.SignUpCheckActivity

class SignUpActivity: BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate), SignUpView, View.OnClickListener {

    override fun initAfterBinding() {
        binding.signupBackBtn.setOnClickListener(this)
        binding.signupSignupBtnTv.setOnClickListener(this)
        binding.signupAgreeCheckOnIv.setOnClickListener(this)
        binding.signupAgreeCheckOffIv.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.signupBackBtn -> finish()
            binding.signupSignupBtnTv -> signUp()
            binding.signupAgreeCheckOnIv -> {
                binding.signupAgreeCheckOnIv.visibility=View.INVISIBLE
                binding.signupAgreeCheckOffIv.visibility=View.VISIBLE
            }
            binding.signupAgreeCheckOffIv -> {
                binding.signupAgreeCheckOnIv.visibility=View.VISIBLE
                binding.signupAgreeCheckOffIv.visibility=View.INVISIBLE
            }
        }
    }

    private fun getUser(): User {
        val email: String =
            binding.signupEmailEt.text.toString()
//        + "@" + binding.signUpDirectInputEt.text.toString()
        val pwd: String = binding.signupPwEt.text.toString()
        val name: String = binding.signupNameEt.text.toString()

        return User(email, imgUrl = "", name, pwd,"")
    }

    private fun signUp() {
        if (binding.signupEmailEt.text.toString()
                .isEmpty()
        ) {
            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signupNameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signupPwEt.text.toString() != binding.signupPwCheckEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        AuthService.signUp(this, getUser())
    }

    override fun onSignUpLoading() {
//        binding.signUpLoadingPb.visibility = View.VISIBLE
    }

    override fun onSignUpSuccess() {
//        binding.signUpLoadingPb.visibility = View.GONE

        startActivityWithClear(SignUpCheckActivity::class.java)
    }

    override fun onSignUpFailure(code: Int, message: String) {
//        binding.signUpLoadingPb.visibility = View.GONE
//
        when(code) {
            401, 402 -> {
                binding.signupEmailErrorTv.visibility = View.VISIBLE
                binding.signupEmailErrorTv.text = message
                Toast.makeText(this, "정확한 정보를 입력해 주세요", Toast.LENGTH_SHORT).show()

          }
        }
    }
}