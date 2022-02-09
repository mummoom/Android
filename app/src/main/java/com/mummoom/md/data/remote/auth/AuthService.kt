package com.mummoom.md.data.remote.auth

import android.util.Log
import com.mummoom.md.ApplicationClass.Companion.TAG
import com.mummoom.md.ApplicationClass.Companion.retrofit
import com.mummoom.md.data.entities.User
import com.mummoom.md.ui.login.LoginView
import com.mummoom.md.ui.main.mypage.MypageView
import com.mummoom.md.ui.siginup.SignUpView
import com.mummoom.md.ui.splash.SplashView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AuthService {
    fun signUp(signUpView: SignUpView, user: User) {
        val authService = retrofit.create(AuthRetrofitInterface::class.java)

        signUpView.onSignUpLoading()

        authService.signUp(user).enqueue(object : Callback<SignupResponse> {
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {

                Log.d("SIGHUP",user.toString())
                val resp = response.body()!!
                Log.d("REPONSE",response.body().toString())

                when(resp.code){
                    1000 -> signUpView.onSignUpSuccess()
                    else -> signUpView.onSignUpFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                signUpView.onSignUpFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
    }

    fun login(loginView: LoginView, user: User) {
        val authService = retrofit.create(AuthRetrofitInterface::class.java)

        loginView.onLoginLoading()

        authService.login(user).clone().enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                val resp = response.body()!!



                when(resp.code){
                    1000 -> loginView.onLoginSuccess(resp.data!!)
                    else -> loginView.onLoginFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                loginView.onLoginFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
    }

    fun autoLogin(splashView: SplashView) {
        val authService = retrofit.create(AuthRetrofitInterface::class.java)

        splashView.onAutoLoginLoading()

        authService.autoLogin().enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                val resp = response.body()!!

                when(resp.code){
                    200 -> splashView.onAutoLoginSuccess()
                    else -> splashView.onAutoLoginFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                splashView.onAutoLoginFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
    }


}