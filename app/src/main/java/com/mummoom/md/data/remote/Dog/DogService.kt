package com.mummoom.md.data.remote.Dog

import android.util.Log
import com.mummoom.md.ApplicationClass.Companion.TAG
import com.mummoom.md.ApplicationClass.Companion.retrofit
import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.entities.User
import com.mummoom.md.ui.doggender.DogInfoView
import com.mummoom.md.ui.login.LoginView
import com.mummoom.md.ui.siginup.SignUpView
import com.mummoom.md.ui.splash.SplashView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DogService {
    fun dogInfo(dogInfoView: DogInfoView, dog : Dog) {
        val dogService = retrofit.create(DogRetrofitInterface::class.java)

        dogInfoView.onDogInfoLoading()

        dogService.dogInfo(dog).enqueue(object : Callback<DogResponse> {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {

                val resp = response.body()!!

                when(resp.code){
                    200 -> dogInfoView.onDogInfoSuccess()
                    else -> dogInfoView.onDogInfoFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                dogInfoView.onDogInfoFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
    }


}