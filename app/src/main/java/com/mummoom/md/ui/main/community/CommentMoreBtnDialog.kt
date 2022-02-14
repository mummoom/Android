package com.mummoom.md.ui.main.community

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.mummoom.md.R

class CommentMoreBtnDialog(context: Context) {

    private val dialog = Dialog(context)

    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_comment_morebtn)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val warningIcon = dialog.findViewById<ImageView>(R.id.commentMoreBtn_warningIcon_iv)
        val warningTv = dialog.findViewById<TextView>(R.id.commentMoreBtn_warning_tv)

        val deleteIcon = dialog.findViewById<ImageView>(R.id.commentMoreBtn_deleteIcon_iv)
        val deleteTv = dialog.findViewById<TextView>(R.id.commentMoreBtn_delete_tv)

        warningIcon.setOnClickListener {
            onClickedListener.onWarningComment()
            dialog.dismiss()
        }

        warningTv.setOnClickListener {
            onClickedListener.onWarningComment()
            dialog.dismiss()
        }

        deleteIcon.setOnClickListener {
            onClickedListener.onDeleteComment()
            dialog.dismiss()
        }

        deleteTv.setOnClickListener {
            onClickedListener.onDeleteComment()
            dialog.dismiss()
        }

        dialog.show()
    }

    // 확인 버튼 누를 때 입력 값들을 처리하기 위한 함수들
    interface clickListener{
        fun onWarningComment()
        fun onDeleteComment()
    }
    private lateinit var onClickedListener : clickListener

    fun setOnClickedListener(listener : clickListener)
    {
        onClickedListener = listener
    }

}