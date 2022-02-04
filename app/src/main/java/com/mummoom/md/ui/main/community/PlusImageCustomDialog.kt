package com.mummoom.md.ui.main.community

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.mummoom.md.R

class PlusImageCustomDialog(context: Context) {

    private val dialog = Dialog(context)

    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_plusimage)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val pictureTv = dialog.findViewById<TextView>(R.id.plusimageCustom_bringPicture_tv)
        val videoTv = dialog.findViewById<TextView>(R.id.plusimageCustom_bringVideo_tv)
        val icon1 = dialog.findViewById<ImageView>(R.id.plusimageCustom_icon1_iv)
        val icon2 = dialog.findViewById<ImageView>(R.id.plusimageCustom_icon2_iv)

        icon1.setOnClickListener {
            onClickedListener.onPictureClicked()
            dialog.dismiss()
        }

        pictureTv.setOnClickListener {
            onClickedListener.onPictureClicked()
            dialog.dismiss()
        }


        icon2.setOnClickListener {
            onClickedListener.onVideoClicked()
            dialog.dismiss()
        }

        videoTv.setOnClickListener {
            onClickedListener.onVideoClicked()
            dialog.dismiss()
        }

        dialog.show()
    }

    // 확인 버튼 누를 때 입력 값들을 처리하기 위한 함수들
    interface clickListener{
        fun onPictureClicked()
        fun onVideoClicked()
    }
    private lateinit var onClickedListener : clickListener

    fun setOnClickedListener(listener : clickListener)
    {
        onClickedListener = listener
    }

}