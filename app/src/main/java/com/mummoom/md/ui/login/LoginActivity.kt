package com.mummoom.md.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.mummoom.md.ApplicationClass.Companion.TAG
import com.mummoom.md.R
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.auth.Auth
import com.mummoom.md.data.remote.auth.AuthService
import com.mummoom.md.databinding.ActivityLoginBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogname.DognameActivity
import com.mummoom.md.ui.main.MainActivity
import com.mummoom.md.ui.siginup.SignUpActivity
import com.mummoom.md.ui.tos.TosActivity
import com.mummoom.md.utils.saveJwt


class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginView, View.OnClickListener ,GoogleLoginView{
    //Configure Google Sign In

    private lateinit var client: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var accessToken : String
    private var hideState : Boolean = true

    private var RC_SIGN_IN = 9001
    private lateinit var authResultLauncher: ActivityResultLauncher<Intent>

    override fun initAfterBinding() {
        binding.loginEmailSignupBtnTv.setOnClickListener(this)
        binding.loginLoginBtnTv.setOnClickListener(this)
        binding.loginGoogleBtn.setOnClickListener {
            googleLogin()
        }
        binding.loginPwHideIv.setOnClickListener {
            hidePwd(hideState)
        }

    }

    private fun hidePwd(newPwdState : Boolean)
    {
        hideState = !newPwdState

        if(hideState)  // ?????? ??????
        {
            binding.loginPwHideIv.setImageResource(R.drawable.btn_input_password)
            binding.loginPwEt.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        else  // ??? ?????? ??????
        {
            binding.loginPwHideIv.setImageResource(R.drawable.btn_input_password_off)
            binding.loginPwEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

         //????????? ?????? ????????? ????????? ?????? ??? ?????? ?????????????????? ???????????? ?????? ??????
        if(auth.currentUser != null)
        {


            val intent = Intent(this, DognameActivity::class.java)
            startActivity(intent)
            finish()
        }

        client = GoogleSignIn.getClient(this, gso)


        authResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->

            val data: Intent? = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {

                val account = task.getResult(ApiException::class.java)

                Log.d("signin", "success")
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.d("signin", "failure")
                Log.e("task", "error", e)

            }

        }
    }

    // ?????? ????????? ?????? ?????? ???
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


    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?){

        val credential = GoogleAuthProvider.getCredential(account?.idToken!!, null)

        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {  //?????? ????????? ??? ??? ???????????? ??????
                    task ->
                if (task.isSuccessful) {
                    // ????????? ????????? ????????? ???!
                    Log.d("signin", "success2")

                    accessToken = account.idToken.toString()
                    Log.d("TOKEN",accessToken)



                    val user = FirebaseAuth.getInstance().currentUser
                    updateUI(user)
                    AuthService.googleLogin(this,accessToken)

                } else {
                    // ????????? ??? ??????!
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    // ????????? ????????? ????????? ?????? ??? ??????
    private  fun updateUI(user: FirebaseUser?) {

        if (user != null) {
//            val intent = Intent(this, DognameActivity::class.java)
//            startActivity(intent)
//            finish()
        }

    }
    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.loginEmailSignupBtnTv-> startNextActivity(TosActivity::class.java) //???????????? ???????????? ??????
            binding.loginLoginBtnTv -> login() //????????? ??????

        }
    }

    private fun login() {
        if (binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "???????????? ??????????????????.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.loginPwEt.text.toString().isEmpty()) {
            Toast.makeText(this, "??????????????? ??????????????????.", Toast.LENGTH_SHORT).show()
            return
        }

        val email = binding.loginIdEt.text.toString()
        val password = binding.loginPwEt.text.toString()
        val user = User(email,"", "",password,"","")

        AuthService.login(this, user)

    }


    override fun onLoginLoading() {
        binding.loginRotateIv.visibility = View.VISIBLE
        binding.loginLoadingIv.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this,R.anim.rotate)
        binding.loginRotateIv.startAnimation(animation)

    }

    override fun onLoginSuccess(auth : Auth) {
//        binding.loginLoadingPb.visibility = View.GONE
        binding.loginRotateIv.visibility = View.GONE
        binding.loginLoadingIv.visibility = View.GONE
        binding.loginRotateIv.clearAnimation()

        saveJwt(auth.token)
        Log.d("${TAG}/JWT-CLEAR", auth.token)
        if(auth.dog_exist) {
            startActivityWithClear(MainActivity::class.java)
        }
        else {
            startActivityWithClear(DognameActivity::class.java)
        }


    }

    override fun onLoginFailure(code: Int, message: String) {
        binding.loginRotateIv.visibility = View.GONE
        binding.loginLoadingIv.visibility = View.GONE
        binding.loginRotateIv.clearAnimation()

        when(code) {
            7003,7004-> {
                Toast.makeText(this, "????????? ????????? ????????? ?????????", Toast.LENGTH_SHORT).show()
//                binding.loginErrorTv.visibility = View.VISIBLE
//                binding.loginErrorTv.text= message
            }
            7006 -> {
                binding.loginEmailErrorTv.visibility=View.VISIBLE
                binding.loginEmailErrorTv.text=message

            }
            7007 -> {
                binding.loginPwErrorTv.visibility=View.VISIBLE
                binding.loginPwErrorTv.text=message

            }
        }
    }

    override fun onGoogleLoginLoading() {

    }

    override fun onGoogleLoginSuccess(auth: Auth) {
        saveJwt(auth.token)
        if(auth.dog_exist) {
            startActivityWithClear(MainActivity::class.java)
        }
        else {
            startActivityWithClear(DognameActivity::class.java)
        }

    }

    override fun onGoogleLoginFailure(code: Int, message: String) {

    }
}
