package com.mummoom.md.ui.main.mypage

import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.mummoom.md.R
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.User.UserService
import com.mummoom.md.data.remote.auth.AuthService
import com.mummoom.md.databinding.ActivityMyprofileBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.login.LoginActivity
import com.mummoom.md.ui.main.community.MypageCustomDialog

class MyProfileActivity : BaseActivity<ActivityMyprofileBinding>(ActivityMyprofileBinding::inflate),MyprofileView,ChangeprofileView {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient : GoogleSignInClient


    override fun initAfterBinding() {


        val changeImageDialog = ChangeImageCustomDialog(this)



        // 이미지 추가/변경 버튼 눌렀을 때
        binding.myprofilePlusBtnIv.setOnClickListener {
            changeImageDialog.MyDig()
        }

        // 비밀번호 edittext 부분 눌렀을 때
        binding.myprofilePwdEt.setOnClickListener {
            val intent = Intent(this, ChangePwdActivity::class.java)
            startActivity(intent)
        }

        // 뒤로가기 눌렀을 때
        binding.myprofileBackBtnIv.setOnClickListener {
            changeUserInfo()

        }

        // 회원탈퇴 눌렀을 때
        binding.myprofileWithdrawTv.setOnClickListener {
            //deleteUser()
//            Toast.makeText(this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show()
//            finish()

            startActivityWithClear(WithdrawActivity::class.java)
        }


        changeImageDialog.setOnClickedListener(object : ChangeImageCustomDialog.clickListener{
            override fun onPictureClicked() {
                if(ContextCompat.checkSelfPermission(applicationContext,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {

                }

            }

            override fun onIllustClicked() {
                startActivityWithClear(IllustrationActivity::class.java)
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
        binding.myprofileEmailContentTv.setText(user.email)
        binding.myprofileNameContentTv.setText(user.nickName)
        //binding.myprofilePwdContentTv.text=user.password

    }
    fun changeUserInfo() {
        if (binding.myprofileEmailContentTv.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.myprofileNameContentTv.text.toString().isEmpty()) {
            Toast.makeText(this, "이름를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val email = binding.myprofileEmailContentTv.text.toString()
        val nickname = binding.myprofileNameContentTv.text.toString()
        Log.d("NICKNAME",nickname)
        val user = User(email,"",nickname,"","","")

        UserService.changeUserInfo(this, user)
    }

    override fun onMyprofileLoading() {

    }

    override fun onMyprofileSuccess(user: User) {
        getUser(user)
    }

    override fun onMyprofileFailure(code: Int, message: String) {

    }

    override fun onChangeprofileLoading() {

    }

    override fun onChangeprofileSuccess() {
        finish()
    }

    override fun onChangeprofileFailure(code: Int, message: String) {

    }


}