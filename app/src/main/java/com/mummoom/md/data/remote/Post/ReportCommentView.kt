package com.mummoom.md.data.remote.Post

interface ReportCommentView {
    fun onReportCommentLoading()
    fun onReportCommentSuccess()
    fun onReportCommentFailure(code : Int, message : String)
}