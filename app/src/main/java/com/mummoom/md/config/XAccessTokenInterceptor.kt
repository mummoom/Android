package com.mummoom.md.config

import com.mummoom.md.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.mummoom.md.utils.getJwt
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class XAccessTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()

        val jwtToken: String? = getJwt() //디바이스에 jwt가 있을 때 가져옴

        jwtToken?.let{
            builder.addHeader(X_ACCESS_TOKEN, jwtToken) //null이 아닐때 헤더에 넣어줌
        }

        return chain.proceed(builder.build())
    }
}