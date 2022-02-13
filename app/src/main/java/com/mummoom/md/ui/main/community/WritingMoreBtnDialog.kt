package com.mummoom.md.ui.main.community

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.mummoom.md.R

class WritingMoreBtnDialog(context: Context) {

    private val dialog = Dialog(context)

    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_writing_morebtn)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val warningIcon = dialog.findViewById<ImageView>(R.id.moreBtnDialog_warningIcon_iv)
        val warningTv = dialog.findViewById<TextView>(R.id.moreBtnDialog_warning_tv)

        val editIcon = dialog.findViewById<ImageView>(R.id.moreBtnDialog_editIcon_iv)
        val editTv = dialog.findViewById<TextView>(R.id.moreBtnDialog_edit_tv)

        val deleteIcon = dialog.findViewById<ImageView>(R.id.moreBtnDialog_deleteIcon_iv)
        val deleteTv = dialog.findViewById<TextView>(R.id.moreBtnDialog_delete_tv)

        warningIcon.setOnClickListener {
            onClickedListener.onReportPost()
            dialog.dismiss()
        }

        warningTv.setOnClickListener {
            onClickedListener.onReportPost()
            dialog.dismiss()
        }

        editIcon.setOnClickListener {
            onClickedListener.onEditPost()
            dialog.dismiss()
        }

        editTv.setOnClickListener {
            onClickedListener.onEditPost()
            dialog.dismiss()
        }

        deleteIcon.setOnClickListener {
            onClickedListener.onDeletePost()
            dialog.dismiss()
        }

        deleteTv.setOnClickListener {
            onClickedListener.onDeletePost()
            dialog.dismiss()
        }

        dialog.show()
    }

    // 확인 버튼 누를 때 입력 값들을 처리하기 위한 함수들
    interface clickListener{
        fun onReportPost()
        fun onEditPost()
        fun onDeletePost()
    }
    private lateinit var onClickedListener : clickListener

    fun setOnClickedListener(listener : clickListener)
    {
        onClickedListener = listener
    }

}