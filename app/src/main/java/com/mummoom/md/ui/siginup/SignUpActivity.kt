package com.mummoom.md.ui.siginup

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.mummoom.md.R
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.auth.AuthService
import com.mummoom.md.databinding.ActivitySignupBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.signupCheck.SignUpCheckActivity

class SignUpActivity: BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate), SignUpView, View.OnClickListener {

    private var hideStatePw : Boolean = true
    private var hideStateCheckPw : Boolean = true

    override fun initAfterBinding() {
        binding.signupBackBtn.setOnClickListener(this)
        binding.signupSignupBtnTv.setOnClickListener(this)
        binding.signupAgreeCheckOnIv.setOnClickListener(this)
        binding.signupAgreeCheckOffIv.setOnClickListener(this)

        binding.signupHidepwIv.setOnClickListener {
            hidePwd(hideStatePw)
        }

        binding.signupHideCheckpwIv.setOnClickListener {
            hideCheckPwd(hideStateCheckPw)
        }
    }

    // 비밀번호 숨김 함수
    private fun hidePwd(newPwdState : Boolean)
    {
        hideStatePw = !newPwdState

        if(hideStatePw)  // 숨김 상태
        {
            binding.signupHidepwIv.setImageResource(R.drawable.btn_input_password)
            binding.signupPwEt.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        else  // 안 숨김 상태
        {
            binding.signupHidepwIv.setImageResource(R.drawable.btn_input_password_off)
            binding.signupPwEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
    }

    // 재확인 비밀번호 숨김 함수
    private fun hideCheckPwd(newPwdState : Boolean)
    {
        hideStateCheckPw = !newPwdState

        if(hideStateCheckPw)  // 숨김 상태
        {
            binding.signupHideCheckpwIv.setImageResource(R.drawable.btn_input_password)
            binding.signupPwCheckEt.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        else  // 안 숨김 상태
        {
            binding.signupHideCheckpwIv.setImageResource(R.drawable.btn_input_password_off)
            binding.signupPwCheckEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
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

        return User(email, imgUrl = "", name, pwd,"","")
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
            7007 -> {
                binding.signupPwErrorTv.visibility = View.VISIBLE
                binding.signupPwErrorTv.text = message

            }
            7010-> {                Toast.makeText(this, "정확한 정보를 입력해 주세요", Toast.LENGTH_SHORT).show()
            }
            7011 -> {
                binding.signupNameErrorTv.visibility = View.VISIBLE
                binding.signupNameErrorTv.text = message
            }
            7012 -> {
                binding.signupEmailErrorTv.visibility = View.VISIBLE
                binding.signupEmailErrorTv.text = message

          }
        }
    }
}