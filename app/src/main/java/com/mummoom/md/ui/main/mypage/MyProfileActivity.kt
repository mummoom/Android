package com.mummoom.md.ui.main.mypage

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.mummoom.md.R
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.User.UserService
import com.mummoom.md.databinding.ActivityMyprofileBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.login.LoginActivity
import com.mummoom.md.ui.main.community.MypageCustomDialog

class MyProfileActivity : BaseActivity<ActivityMyprofileBinding>(ActivityMyprofileBinding::inflate),MyprofileView {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient : GoogleSignInClient

    override fun initAfterBinding() {


        val changeImageDialog = ChangeImageCustomDialog(this)



        // 이미지 추가/변경 버튼 눌렀을 때
        binding.myprofilePlusBtnIv.setOnClickListener {
            changeImageDialog.MyDig()
        }

        // 비밀번호 edittext 부분 눌렀을 때
        binding.myprofilePwdContentTv.setOnClickListener {
            val intent = Intent(this, ChangePwdActivity::class.java)
            startActivity(intent)
        }

        // 뒤로가기 눌렀을 때
        binding.myprofileBackBtnIv.setOnClickListener {
            finish()
        }

        // 회원탈퇴 눌렀을 때
        binding.myprofileWithdrawTv.setOnClickListener {
            deleteUser()
            Toast.makeText(this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show()
            finish()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        changeImageDialog.setOnClickedListener(object : ChangeImageCustomDialog.normalBtnClickListener{
            override fun onClicked() {
                val intent = Intent(this@MyProfileActivity,IllustrationActivity::class.java)
                startActivity(intent)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        UserService.getUserByIdx(this)

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

    fun getUser(user: User) {

        //binding.myprofileProfileImgIv.setImageURI(user.imgUrl.)
        binding.myprofileEmailContentTv.text=user.email
        binding.myprofileNameContentTv.text=user.name
        //binding.myprofilePwdContentTv.text=user.password

    }

    override fun onMyprofileLoading() {

    }

    override fun onMyprofileSuccess(user: User) {
        getUser(user)
    }

    override fun onMyprofileFailure(code: Int, message: String) {

    }


}