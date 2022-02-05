package com.mummoom.md.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mummoom.md.R
import com.mummoom.md.data.remote.auth.Auth
import com.mummoom.md.databinding.ActivityLoginBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.main.MainActivity
import com.mummoom.md.ui.nickname.NicknameActivity
import com.mummoom.md.ui.siginup.SignUpActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.auth.AuthService
import com.mummoom.md.ui.dogname.DognameActivity
import com.mummoom.md.utils.saveJwt


class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginView, View.OnClickListener {
    //Configure Google Sign In

    private lateinit var client: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    private var RC_SIGN_IN = 9001
    private lateinit var authResultLauncher: ActivityResultLauncher<Intent>

    override fun initAfterBinding() {
        binding.loginEmailSignupBtnTv.setOnClickListener(this)
        binding.loginLoginBtnTv.setOnClickListener(this)
        binding.loginGoogleBtn.setOnClickListener {
            googleLogin()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

         //로그인 하고 다음에 어플을 켰을 때 바로 다음화면으로 넘어가게 하는 코드
        if(auth.currentUser != null)
        {
            val intent = Intent(this, NicknameActivity::class.java)
            startActivity(intent)
            finish()
        }

        client = GoogleSignIn.getClient(this, gso)

        authResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            // if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
//            if(result.resultCode == RC_SIGN_IN)
//            {
//
//            }
            val data: Intent? = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data) as GoogleSignInAccount
            try {
                // Google Sign In was successful, authenticate with Firebase
//                val account = task.getResult(ApiException::class.java)!!
                val account = task.getResult(ApiException::class.java)

//                Log.d("firebaseAuthWithGoogle:" + account.id)
                Log.d("signin", "success")
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.d("signin", "failure")
                Log.e("task", "error", e)
                // Google Sign In failed, update UI appropriately
                //Timber.w(e, "Google sign in failed")
            }

            // }
        }
    }

    // 구글 로그인 버튼 클릭 시
    private fun googleLogin() {
        val signInIntent = client.signInIntent
        authResultLauncher.launch(signInIntent)
    }

    override fun onStart() {
        super.onStart()

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == 1004)
//        {
//            if(resultCode == Activity.RESULT_OK)
//            {
//                val result = GoogleSignIn.getSignedInAccountFromIntent(data)
//
//                val account = result.getResult(ApiException::class.java)
//                firebaseAuthWithGoogle(account)
//            }
//        }
//    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?){

        val credential = GoogleAuthProvider.getCredential(account?.idToken!!, null)

        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {  //통신 완료가 된 후 무슨일을 할지
                    task ->
                if (task.isSuccessful) {
                    // 로그인 처리를 해주면 됨!
                    Log.d("signin", "success2")

                    val user = FirebaseAuth.getInstance().currentUser
                    updateUI(user)

                } else {
                    // 오류가 난 경우!
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode==RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult((ApiException::class.java))!!
//                Log.d("LoginActivity","firebaseAuthWithGoogle: "+account.id)
//                firebaseAuthWithGoogle(account.idToken!!)
//            }catch (e : ApiException) {
//                Log.w("LoginActivity","Google sign in failed ",e)
//            }
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(idToken : String) {
//        val credential = GoogleAuthProvider.getCredential(idToken,null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task->
//                if(task.isSuccessful) {  // 로그인 성공시
//                    Log.d("LoginActivity","signInWithCredential:success")
//                    val user = auth.currentUser
//                    val email = auth.currentUser?.email
//
//                    updateUI(user)
//                }
//                else {
//                    Log.w("LoginActivity","signInWithCredential:failure",task.exception)
//                    updateUI(null)
//                }
//            }
//    }

    // 로그인 성공시 이후에 실행 할 코드
    private  fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            val intent = Intent(this, NicknameActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.loginEmailSignupBtnTv-> startNextActivity(SignUpActivity::class.java) //이메일로 회원가입 버튼
            binding.loginLoginBtnTv -> startNextActivity(DognameActivity::class.java)//login() //로그인 버튼
//            binding.loginGoogleBtn->googleLogin()
        }
    }

    private fun signIn() {

    }

    private fun login() {
        if (binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.loginPwEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val email = binding.loginIdEt.text.toString()
        val password = binding.loginPwEt.text.toString()
        val user = User(email,"", password, "")

        AuthService.login(this, user)


    }




    override fun onLoginLoading() {
        //binding.loginLoadingPb.visibility = View.VISIBLE
    }

    override fun onLoginSuccess(auth: Auth) {
//        binding.loginLoadingPb.visibility = View.GONE
//
        saveJwt(auth.jwt)
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
