package com.mummoom.md.data.remote.User

import android.util.Log
import com.mummoom.md.ApplicationClass.Companion.TAG
import com.mummoom.md.ApplicationClass.Companion.retrofit
import com.mummoom.md.data.entities.User
import com.mummoom.md.ui.login.LoginView
import com.mummoom.md.ui.main.mypage.MypageView
import com.mummoom.md.ui.main.mypage.MyprofileView
import com.mummoom.md.ui.siginup.SignUpView
import com.mummoom.md.ui.splash.SplashView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserService {

    fun getUserByIdx(myprofileView: MyprofileView) {
        val userService = retrofit.create(UserRetrofitInterface::class.java)

        myprofileView.onMyprofileLoading()

        userService.getUserByIdx().clone().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val resp = response.body()!!
                Log.d("RESPONSE_BODY",resp.data.toString())


                when(resp.code){
                    1000 -> myprofileView.onMyprofileSuccess(resp.data!!)
                    else -> myprofileView.onMyprofileFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                myprofileView.onMyprofileFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
    }
}