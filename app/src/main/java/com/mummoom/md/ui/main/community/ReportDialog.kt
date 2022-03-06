package com.mummoom.md.ui.main.community

import android.app.Dialog
import android.content.Context
import android.view.View
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

        val abuseOnBtn = dialog.findViewById<ImageView>(R.id.reportDialog_abuse_iv_on)
        val abuseOffBtn = dialog.findViewById<ImageView>(R.id.reportDialog_abuse_iv_off)
        val promotionOnBtn = dialog.findViewById<ImageView>(R.id.reportDialog_promotion_iv_on)
        val promotionOffBtn = dialog.findViewById<ImageView>(R.id.reportDialog_promotion_iv_off)
        val obscenityOnBtn = dialog.findViewById<ImageView>(R.id.reportDialog_obscenity_iv_on)
        val obscenityOffBtn = dialog.findViewById<ImageView>(R.id.reportDialog_obscenity_iv_off)
        val repeatOnBtn = dialog.findViewById<ImageView>(R.id.reportDialog_repeat_iv_on)
        val repeatOffBtn = dialog.findViewById<ImageView>(R.id.reportDialog_repeat_iv_off)
        val otherOnBtn = dialog.findViewById<ImageView>(R.id.reportDialog_other_iv_on)
        val otherOffBtn = dialog.findViewById<ImageView>(R.id.reportDialog_other_iv_off)

        //욕설
        abuseOnBtn.setOnClickListener {
            abuseOffBtn.visibility= View.INVISIBLE
            abuseOnBtn.visibility=View.VISIBLE
        }
        abuseOffBtn.setOnClickListener {
            abuseOffBtn.visibility=View.VISIBLE
            abuseOnBtn.visibility= View.INVISIBLE
        }
        //홍보성
        promotionOnBtn.setOnClickListener {
            promotionOnBtn.visibility=View.VISIBLE
            promotionOffBtn.visibility= View.INVISIBLE
        }
        promotionOffBtn.setOnClickListener {
            promotionOffBtn.visibility=View.VISIBLE
            promotionOnBtn.visibility= View.INVISIBLE
        }
        //음란성
        obscenityOnBtn.setOnClickListener {
            obscenityOnBtn.visibility=View.VISIBLE
            obscenityOffBtn.visibility= View.INVISIBLE
        }
        obscenityOffBtn.setOnClickListener {
            obscenityOnBtn.visibility=View.VISIBLE
            obscenityOffBtn.visibility= View.INVISIBLE
        }
        //도배
        repeatOnBtn.setOnClickListener {
            repeatOnBtn.visibility=View.VISIBLE
            repeatOffBtn.visibility= View.INVISIBLE
        }
        repeatOffBtn.setOnClickListener {
            repeatOnBtn.visibility=View.VISIBLE
            repeatOffBtn.visibility= View.INVISIBLE
        }
        //기타
        otherOnBtn.setOnClickListener {
            otherOnBtn.visibility=View.VISIBLE
            otherOffBtn.visibility= View.INVISIBLE
        }
        otherOffBtn.setOnClickListener {
            otherOnBtn.visibility=View.VISIBLE
            otherOffBtn.visibility= View.INVISIBLE
        }

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