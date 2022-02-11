package com.mummoom.md.ui.main.mypage

import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.mummoom.md.R
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.entities.WIthdrawUser
import com.mummoom.md.data.remote.User.UserService
import com.mummoom.md.data.remote.auth.AuthService
import com.mummoom.md.databinding.ActivityWithdrawBinding
import com.mummoom.md.databinding.ActivityWithdrawalBinding
import com.mummoom.md.ui.BaseActivity

class WithdrawActivity : BaseActivity<ActivityWithdrawBinding>(ActivityWithdrawBinding::inflate),WithdrawView,View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient : GoogleSignInClient

    override fun initAfterBinding() {
        binding.withdrawBackarrowBtn.setOnClickListener(this)
        binding.withdrawWithdrawBtn.setOnClickListener(this)




    }
    override fun onClick(v: View?) {
        if(v==null) return

        when(v) {
            binding.withdrawBackarrowBtn -> finish()
            binding.withdrawWithdrawBtn -> withdraw()
        }
    }

    private fun withdraw() {
        if (binding.withdrawPwEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.withdrawPwEt.text.toString() != binding.withdrawCheckpwEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.withdrawWhyEt.text.toString().isEmpty()) {
            Toast.makeText(this, "탈퇴 사유를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }


        val withdrawPassword = binding.withdrawPwEt.text.toString()
        val withdrawReason = binding.withdrawWhyEt.text.toString()
        val wIthdrawUser = WIthdrawUser(withdrawPassword,withdrawReason)

        UserService.withdrawUser(this,wIthdrawUser)



    }
    private fun deleteUser()
    {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSingInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        auth.signOut()
        googleSingInClient?.signOut()

        user?.delete()
        googleSingInClient.revokeAccess()
    }
    override fun onWithdrawLoading() {

    }

    override fun onWithdrawSuccess() {
        //deleteUser()
        startActivityWithClear(WithdrawalActivity::class.java)

    }

    override fun onWithdrawFailure(code: Int, message: String) {
        when(code) {
            7007 -> {
                binding.withdrawPwErrorTv.visibility= View.VISIBLE
                binding.withdrawPwErrorTv.text=message
            }
        }
    }


}