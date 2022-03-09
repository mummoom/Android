package com.mummoom.md.ui.main.community

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.TextView
import com.mummoom.md.R

class BlockDialog(context: Context) {

    private val dialog = Dialog(context)

    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_user_block)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val doneBtn = dialog.findViewById<TextView>(R.id.blockDialog_doneBtn_btn)
        val cancelBtn = dialog.findViewById<TextView>(R.id.blockDialog_cancelBtn_btn)


        // 확인 버튼 눌렀을 때
        doneBtn.setOnClickListener {
            onClickedListener.onDoneClicked()
            dialog.dismiss()
        }

        // 취소 버튼 눌렀을 때
        cancelBtn.setOnClickListener {
            onClickedListener.onCancelClicked()
            dialog.dismiss()
        }

        dialog.show()
    }

    interface clickListener{
        fun onDoneClicked()
        fun onCancelClicked()
    }

    private lateinit var onClickedListener : clickListener

    fun setOnClickedListener(listener : clickListener)
    {
        onClickedListener = listener
    }
}