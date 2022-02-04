package com.mummoom.md.data.remote.Ingredients

import android.util.Log
import com.mummoom.md.ApplicationClass.Companion.TAG
import com.mummoom.md.ApplicationClass.Companion.retrofit
import com.mummoom.md.data.Ingredients.Ingredients
import com.mummoom.md.ui.main.home.IngredientsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object IngredientsService {
//    fun getIngredientsCategory(ingredientsView: IngredientsView, ingredients: Ingredients) {
//        val ingredientsService = retrofit.create(IngredientsRetrofitInterface::class.java)
//
//        ingredientsView.onIngredientsLoading()
//
//        ingredientsService.getIngredientsCategory(ingredients).enqueue(object : Callback<IngredientsResponse> {
//            override fun onResponse(call: Call<IngredientsResponse>, response: Response<IngredientsResponse>) {
//
//                val resp = response.body()!!
//
//                when(resp.status){
//                    200 -> ingredientsView.onIngredientsSuccess()
//                    else -> ingredientsView.onIngredientsFailure(resp.status, resp.message)
//
//                }
//            }
//
//            override fun onFailure(call: Call<IngredientsResponse>, t: Throwable) {
//                Log.d("$TAG/API-ERROR", t.message.toString())
//
//                ingredientsView.onIngredientsFailure(400, "네트워크 오류가 발생했습니다.")
//                ingredientsView.onIngredientsFailure(401, "Unauthorized")
//                ingredientsView.onIngredientsFailure(402, "Forbidden")
//                ingredientsView.onIngredientsFailure(403, "Not Found")
//
//            }
//        })
//    }

}
//    fun signUp(signUpView: SignUpView, user: User) {
//        val authService = retrofit.create(AuthRetrofitInterface::class.java)
//
//        signUpView.onSignUpLoading()
//
//        authService.signUp(user).enqueue(object : Callback<AuthResponse> {
//            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
//
//                val resp = response.body()!!
//
//                when(resp.code){
//                    1000 -> signUpView.onSignUpSuccess()
//                    else -> signUpView.onSignUpFailure(resp.code, resp.message)
//                }
//            }
//
//            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                Log.d("$TAG/API-ERROR", t.message.toString())
//
//                signUpView.onSignUpFailure(400, "네트워크 오류가 발생했습니다.")
//            }
//        })
//    }
//
//    fun login(loginView: LoginView, user: User) {
//        val authService = retrofit.create(AuthRetrofitInterface::class.java)
//
//        loginView.onLoginLoading()
//
//        authService.login(user).enqueue(object : Callback<AuthResponse> {
//            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
//                val resp = response.body()!!
//
//                when(resp.code){
//                    1000 -> loginView.onLoginSuccess(resp.result!!)
//                    else -> loginView.onLoginFailure(resp.code, resp.message)
//                }
//            }
//
//            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                Log.d("$TAG/API-ERROR", t.message.toString())
//
//                loginView.onLoginFailure(400, "네트워크 오류가 발생했습니다.")
//            }
//        })
//    }
//
//    fun autoLogin(splashView: SplashView) {
//        val authService = retrofit.create(AuthRetrofitInterface::class.java)
//
//        splashView.onAutoLoginLoading()
//
//        authService.autoLogin().enqueue(object : Callback<AuthResponse> {
//            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
//                val resp = response.body()!!
//
//                when(resp.code){
//                    1000 -> splashView.onAutoLoginSuccess()
//                    else -> splashView.onAutoLoginFailure(resp.code, resp.message)
//                }
//            }
//
//            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                Log.d("$TAG/API-ERROR", t.message.toString())
//
//                splashView.onAutoLoginFailure(400, "네트워크 오류가 발생했습니다.")
//            }
//        })
//    }
