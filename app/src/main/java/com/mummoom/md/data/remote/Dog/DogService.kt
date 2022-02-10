package com.mummoom.md.data.remote.Dog

import android.util.Log
import com.mummoom.md.ApplicationClass.Companion.TAG
import com.mummoom.md.ApplicationClass.Companion.retrofit
import com.mummoom.md.data.entities.Dog
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.remote.Ingredients.IngredientsView
import com.mummoom.md.ui.doggender.DogInfoView
import com.mummoom.md.ui.login.LoginView
import com.mummoom.md.ui.main.mypage.MypageDogChangeView
import com.mummoom.md.ui.main.mypage.MypageView
import com.mummoom.md.ui.siginup.SignUpView
import com.mummoom.md.ui.splash.SplashView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DogService {
    fun dogInfo(dogInfoView: DogInfoView, dog : Dog) {
        val dogService = retrofit.create(DogRetrofitInterface::class.java)

        Log.d("DOG_",dog.toString())

        dogInfoView.onDogInfoLoading()

        dogService.dogInfo(dog).enqueue(object : Callback<DogResponse> {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                Log.d("DOG_REPONSE",response.body().toString())

                val resp = response.body()!!

                when(resp.code){
                    1000 -> dogInfoView.onDogInfoSuccess(resp.data!!)
                    else -> dogInfoView.onDogInfoFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                dogInfoView.onDogInfoFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
    }
    fun getDoglist(mypageView: MypageView) {
        val dogService = retrofit.create(DogRetrofitInterface::class.java)


        mypageView.onMypageLoading()
        dogService.getDoglist().enqueue(object : Callback<DogListResponse> {
            override fun onResponse(call: Call<DogListResponse>, response: Response<DogListResponse>) {
                Log.d("DOG_REPONSE",response.body().toString())

                val resp = response.body()!!

                when(resp.code){
                    1000 -> mypageView.onMypageSuccess(resp.data)
                    else -> mypageView.onMypageFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<DogListResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                mypageView.onMypageFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })




    }

    private lateinit var mypageDogChangeView: MypageDogChangeView
    fun setMypageDogChangeView( view: MypageDogChangeView)
    {
        mypageDogChangeView = view
    }

    fun changeDog(dogIdx: Int,dog: Dog) {
        val dogService = retrofit.create(DogRetrofitInterface::class.java)


        mypageDogChangeView.onMypageDogchangeLoading()
        dogService.changeDog(dogIdx,dog).enqueue(object : Callback<ChangeDogResponse> {
            override fun onResponse(call: Call<ChangeDogResponse>, response: Response<ChangeDogResponse>) {
                Log.d("DOG_REPONSE",response.body().toString())

                val resp = response.body()!!

                when(resp.code){
                    1000 -> mypageDogChangeView.onMypageDogchangeSuccess()
                    else -> mypageDogChangeView.onMypageDogchangeFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<ChangeDogResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                mypageDogChangeView.onMypageDogchangeFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })




    }


}