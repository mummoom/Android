package com.mummoom.md.ui.main.mypage

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.mummoom.md.R
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.login.LoginActivity

class ChangeImageCustomDialog(context : Context)  {
    private val dialog = Dialog(context)

    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_changeimage)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)


        val albumTv = dialog.findViewById<TextView>(R.id.changeimageCustom_albumFunc_tv)
        val normalTv = dialog.findViewById<TextView>(R.id.changeimageCustom_normalFunc_tv)
        val icon1 = dialog.findViewById<ImageView>(R.id.changeimageCustom_icon1_iv)
        val icon2 = dialog.findViewById<ImageView>(R.id.changeimageCustom_icon2_iv)

        icon1.setOnClickListener {
            dialog.dismiss()
        }

        albumTv.setOnClickListener {
            dialog.dismiss()
        }


        icon2.setOnClickListener {
            dialog.dismiss()
        }

        normalTv.setOnClickListener {
            onClickedListener.onClicked()
            dialog.dismiss()
        }

        dialog.show()
    }

    // 확인 버튼 누를 때 입력 값들을 처리하기 위한 함수들
    interface normalBtnClickListener{
        fun onClicked()
    }
    private lateinit var onClickedListener : ChangeImageCustomDialog.normalBtnClickListener

    fun setOnClickedListener(listener : ChangeImageCustomDialog.normalBtnClickListener)
    {
        onClickedListener = listener
    }
}

