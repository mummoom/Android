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

        val albumTv = dialog.findViewById<TextView>(R.id.plusimageCustom_albumFunc_tv)
        val normalTv = dialog.findViewById<TextView>(R.id.plusimageCustom_normalFunc_tv)
        val icon1 = dialog.findViewById<ImageView>(R.id.plusimageCustom_icon1_iv)
        val icon2 = dialog.findViewById<ImageView>(R.id.plusimageCustom_icon2_iv)

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
            dialog.dismiss()
        }

        dialog.show()
    }

}