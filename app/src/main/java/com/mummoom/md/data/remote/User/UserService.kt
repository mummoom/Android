package com.mummoom.md.data.remote.User

import android.util.Log
import com.mummoom.md.ApplicationClass.Companion.TAG
import com.mummoom.md.ApplicationClass.Companion.retrofit
import com.mummoom.md.data.entities.User
import com.mummoom.md.data.entities.WIthdrawUser
import com.mummoom.md.ui.main.mypage.*
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

    fun changePwd(changepwView: ChangepwView,user: User) {
        val userService = retrofit.create(UserRetrofitInterface::class.java)

        changepwView.onChangepwLoading()

        userService.changePwd(user).enqueue(object : Callback<PwdResponse> {
            override fun onResponse(call: Call<PwdResponse>, response: Response<PwdResponse>) {

                val resp = response.body()!!

                when(resp.code){
                    1000 -> changepwView.onChangepwSuccess()
                    else -> changepwView.onChangepwFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<PwdResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                changepwView.onChangepwFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })

    }
    fun changeUsername(changenameView: ChangenameView, user: User) {
        val userService = retrofit.create(UserRetrofitInterface::class.java)

        changenameView.onChangenameLoading()

        userService.changeUsername(user).enqueue(object : Callback<PwdResponse> {
            override fun onResponse(call: Call<PwdResponse>, response: Response<PwdResponse>) {

                val resp = response.body()!!

                when(resp.code){
                    1000 -> changenameView.onChangenameSuccess()
                    else -> changenameView.onChangenameFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<PwdResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                changenameView.onChangenameFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })

    }

    fun changeUserImg(changeImgView: ChangeImgView, user: User) {
        val userService = retrofit.create(UserRetrofitInterface::class.java)

        changeImgView.onChangeprofileLoading()

        userService.changeUserImg(user.imgUrl).enqueue(object : Callback<PwdResponse> {
            override fun onResponse(call: Call<PwdResponse>, response: Response<PwdResponse>) {

                val resp = response.body()!!

                when(resp.code){
                    1000 -> changeImgView.onChangeprofileSuccess()
                    else -> changeImgView.onChangeprofileFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<PwdResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                changeImgView.onChangeprofileFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })

    }
    fun withdrawUser(withdrawView: WithdrawView,wIthdrawUser: WIthdrawUser) {
        val userService = retrofit.create(UserRetrofitInterface::class.java)

        withdrawView.onWithdrawLoading()

        userService.withdrawUser(wIthdrawUser).enqueue(object : Callback<PwdResponse> {
            override fun onResponse(call: Call<PwdResponse>, response: Response<PwdResponse>) {

                val resp = response.body()!!

                when(resp.code){
                    1000 -> withdrawView.onWithdrawSuccess()
                    else -> withdrawView.onWithdrawFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<PwdResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                withdrawView.onWithdrawFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })

    }

    fun  oauthwithdrawUser(oauthWithdrawView: OauthWithdrawView) {
        val userService = retrofit.create(UserRetrofitInterface::class.java)

        oauthWithdrawView.onOauthWithdrawLoading()

        userService.oauthwithdrawUser().enqueue(object : Callback<PwdResponse> {
            override fun onResponse(call: Call<PwdResponse>, response: Response<PwdResponse>) {

                val resp = response.body()!!

                when(resp.code){
                    1000 -> oauthWithdrawView.onOauthWithdrawSuccess()
                    else -> oauthWithdrawView.onOauthWithdrawFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<PwdResponse>, t: Throwable) {
                Log.d("$TAG/API-ERROR", t.message.toString())

                oauthWithdrawView.onOauthWithdrawFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })

    }


}