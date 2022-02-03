package com.mummoom.md.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mummoom.md.R
import com.mummoom.md.data.remote.auth.Auth
import com.mummoom.md.databinding.ActivityLoginBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.nickname.NicknameActivity
import com.mummoom.md.ui.siginup.SignUpActivity

class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginView, View.OnClickListener {
    //Configure Google Sign In

    private lateinit var client: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    private var RC_SIGN_IN=9001
    private lateinit var authResultLauncher: ActivityResultLauncher<Intent>

    override fun initAfterBinding() {
        binding.loginEmailSignupBtnTv.setOnClickListener(this)
        binding.loginLoginBtnTv.setOnClickListener(this)
        binding.loginGoogleBtn.setOnClickListener(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken("998011155807-32r4ibd4cubivftk8e67pe2ek8q0ss6u.apps.googleusercontent.com")
            .requestEmail()
            .build()

        auth= Firebase.auth
        client=GoogleSignIn.getClient(this,gso)

        authResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                //Log.d("firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                //Timber.w(e, "Google sign in failed")
            }
            // }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult((ApiException::class.java))!!
                Log.d("LoginActivity","firebaseAuthWithGoogle: "+account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            }catch (e : ApiException) {
                Log.w("LoginActivity","Google sign in failed ",e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken : String) {
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task->
                if(task.isSuccessful) {
                    Log.d("LoginActivity","signInWithCredential:success")
                    val user = auth.currentUser
                    val email = auth.currentUser?.email

                    updateUI(user)
                }
                else {
                    Log.w("LoginActivity","signInWithCredential:failure",task.exception)
                    updateUI(null)
                }
            }
    }

    private  fun updateUI(user: FirebaseUser?) {

    }
    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.loginEmailSignupBtnTv-> startNextActivity(SignUpActivity::class.java) //이메일로 회원가입 버튼
            binding.loginLoginBtnTv -> startNextActivity(NicknameActivity::class.java)//login() //로그인 버튼
            binding.loginGoogleBtn->googleLogin()
        }
    }

    private fun login() {
//        if (binding.loginIdEt.text.toString().isEmpty() || binding.loginDirectInputEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        if (binding.loginPasswordEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val email = binding.loginIdEt.text.toString() + "@" + binding.loginDirectInputEt.text.toString()
//        val password = binding.loginPasswordEt.text.toString()
//        val user = User(email, password, "")
//
//        AuthService.login(this, user)


   }


    private fun googleLogin() {
        val signInIntent = client.signInIntent
        authResultLauncher.launch(signInIntent)
    }

    override fun onLoginLoading() {
        //binding.loginLoadingPb.visibility = View.VISIBLE
    }

    override fun onLoginSuccess(auth: Auth) {
//        binding.loginLoadingPb.visibility = View.GONE
//
//        saveJwt(auth.jwt)
//        startActivityWithClear(MainActivity::class.java)
    }

    override fun onLoginFailure(code: Int, message: String) {
//        binding.loginLoadingPb.visibility = View.GONE
//
//        when(code) {
//            2015, 2019, 3014 -> {
//                binding.loginErrorTv.visibility = View.VISIBLE
//                binding.loginErrorTv.text= message
//            }
//        }
    }
}