package com.mummoom.md.ui.main.community

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.mummoom.md.R

class SharingCustomDialog(context : Context) {

    private val dialog = Dialog(context)

    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_sharing)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(1200, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val url = dialog.findViewById<TextView>(R.id.sharing_url_tv)
        val copyBtn = dialog.findViewById<ImageView>(R.id.sharing_copyBtn_iv)

        copyBtn.setOnClickListener {
            // url 변수에 있는 텍스트를 복사하는 함수

            dialog.dismiss()
        }

        dialog.show()
    }

}