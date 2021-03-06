package com.mummoom.md.utils


import com.mummoom.md.ApplicationClass.Companion.X_AUTH_TOKEN
import com.mummoom.md.ApplicationClass.Companion.mSharedPreferences

fun saveJwt(jwtToken: String) {
    val editor = mSharedPreferences.edit()
    editor.putString(X_AUTH_TOKEN, jwtToken)

    editor.apply()
}

fun getJwt(): String? = mSharedPreferences.getString(X_AUTH_TOKEN, null)