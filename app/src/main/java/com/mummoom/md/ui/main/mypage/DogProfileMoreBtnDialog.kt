package com.mummoom.md.ui.main.mypage

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.mummoom.md.R

class DogProfileMoreBtnDialog(context: Context) {

    private val dialog = Dialog(context)

    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_dogprofile_morebtn)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val editIcon = dialog.findViewById<ImageView>(R.id.dogprofileMoreBtn_editIcon_iv)
        val editTv = dialog.findViewById<TextView>(R.id.dogprofileMoreBtn_edit_tv)

        val deleteIcon = dialog.findViewById<ImageView>(R.id.dogprofileMoreBtn_deleteIcon_iv)
        val deleteTv = dialog.findViewById<TextView>(R.id.dogprofileMoreBtn_delete_tv)

        editIcon.setOnClickListener {
            onClickedListener.onEditProfile()
            dialog.dismiss()
        }

        editTv.setOnClickListener {
            onClickedListener.onEditProfile()
            dialog.dismiss()
        }

        deleteIcon.setOnClickListener {
            onClickedListener.onDeleteProfile()
            dialog.dismiss()
        }

        deleteTv.setOnClickListener {
            onClickedListener.onDeleteProfile()
            dialog.dismiss()
        }

        dialog.show()
    }

    // 확인 버튼 누를 때 입력 값들을 처리하기 위한 함수들
    interface clickListener{
        fun onEditProfile()
        fun onDeleteProfile()
    }
    private lateinit var onClickedListener : clickListener

    fun setOnClickedListener(listener : clickListener)
    {
        onClickedListener = listener
    }

}