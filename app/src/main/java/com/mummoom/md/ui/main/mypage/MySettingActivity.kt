package com.mummoom.md.ui.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.mummoom.md.R
import com.mummoom.md.databinding.ActivityMysettingBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.login.LoginActivity

class MySettingActivity : BaseActivity<ActivityMysettingBinding>(ActivityMysettingBinding::inflate) {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient : GoogleSignInClient

    override fun initAfterBinding() {

        // 로그아웃 눌렀을 때
        binding.mysettingLogoutTv.setOnClickListener {
            signOut()
            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_LONG).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.mysettingBackarrowBtn.setOnClickListener {
            finish()
        }
        binding.mysettingNotificationTv.setOnClickListener {
            startActivityWithClear(PushSettingActivity::class.java)
        }
    }

    private fun signOut()
    {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSingInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()

        FirebaseAuth.getInstance().signOut()
        googleSingInClient?.signOut()
    }
}