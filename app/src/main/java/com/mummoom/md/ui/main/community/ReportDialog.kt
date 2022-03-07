package com.mummoom.md.ui.main.community

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.mummoom.md.R

class ReportDialog(context : Context) {

    private val dialog = Dialog(context)
    private lateinit var reason: String

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

        val radioGroup = dialog.findViewById<RadioGroup>(R.id.reportDialog_radio_group)

        radioGroup.setOnCheckedChangeListener{ group, checkedId ->
            when(checkedId)
            {
                R.id.reportDialog_abuse_rb ->
                {
                    reasonEt.visibility = View.GONE
                    reason = "욕설과 명예훼손"
                }
                R.id.reportDialog_promotion_rb ->
                {
                    reasonEt.visibility = View.GONE
                    reason = "홍보성"
                }
                R.id.reportDialog_obscenity_rb ->
                {
                    reasonEt.visibility = View.GONE
                    reason = "음란성"
                }
                R.id.reportDialog_repeat_rb ->
                {
                    reasonEt.visibility = View.GONE
                    reason = "같은 내용 반복 (도배)"
                }
                R.id.reportDialog_other_rb ->
                {
                    reasonEt.visibility = View.VISIBLE
                    reason = reasonEt.text.toString()
                }
            }
        }

        // 확인 버튼 눌렀을 때
        doneBtn.setOnClickListener {
            Log.d("REPORT_Reason", reason)
            onClickedListener.onClicked(reason)
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