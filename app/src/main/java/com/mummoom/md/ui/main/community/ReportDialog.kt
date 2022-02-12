package com.mummoom.md.ui.main.community

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.*
import com.mummoom.md.R

class ReportDialog(context : Context) {

    private val dialog = Dialog(context)


    // dialog 띄우는 함수
    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_report)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val reasonEt = dialog.findViewById<EditText>(R.id.reportDialog_reason_et)
        val doneBtn = dialog.findViewById<TextView>(R.id.reportDialog_doneBtn_btn)
        val cancelBtn = dialog.findViewById<TextView>(R.id.reportDialog_cancelBtn_btn)

        // 확인 버튼 눌렀을 때
        doneBtn.setOnClickListener {
            onClickedListener.onClicked(reasonEt.toString())
            dialog.dismiss()
        }

        // 취소 버튼 눌렀을 때
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


    // 확인 버튼 누를 때 신고사유 보내는 과정
    interface clickListener{
        fun onClicked(reason : String)
    }

    private lateinit var onClickedListener : clickListener

    fun setOnClickedListener(listener : clickListener)
    {
        onClickedListener = listener
    }

}