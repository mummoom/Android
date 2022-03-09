package com.mummoom.md.data.remote.Post

interface ReportUserView {
    fun onReportUserLoading()
    fun onReportUserSuccess(data : Int)
    fun onReportUserFailure(code : Int, message : String)
}