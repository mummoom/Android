package com.mummoom.md.data.remote.Post

interface ReportPostView {
    fun onReportPostLoading()
    fun onReportPostSuccess()
    fun onReportPostFailure(code: Int, message: String)
}