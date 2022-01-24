package com.minux.meommum.ui.main.mypage

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.mummoom.md.R

class MypageCustomDialog(context : Context) {
    private val dialog = Dialog(context)

    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_mypage)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val nameEt = dialog.findViewById<EditText>(R.id.mypageDialog_name_et)
        val speciesEt = dialog.findViewById<EditText>(R.id.mypageDialog_species_et)
        val genderEt = dialog.findViewById<EditText>(R.id.mypageDialog_gender_et)
        val birthEt = dialog.findViewById<EditText>(R.id.mypageDialog_birth_et)

        val btnDone = dialog.findViewById<TextView>(R.id.mypageDialog_doneBtn_btn)

        btnDone.setOnClickListener {
            onClickedListener.onClicked(nameEt.text.toString(), speciesEt.text.toString(),
                genderEt.text.toString(), birthEt.text.toString())

            dialog.dismiss()
        }


        dialog.show()
    }

//    interface ButtonClickListener{
//        fun onClicked(myName : String, mySpecies : String, myGender : String, myBirth : String)
//    }
//
//    private lateinit var onClickedListener : ButtonClickListener
//
//    fun setOnClickedListener(listener : ButtonClickListener)
//    {
//        onClickedListener = listener
//    }

    interface TextClickListener{
        fun onClicked(myName : String, mySpecies : String, myGender : String, myBirth : String)
    }

    private lateinit var onClickedListener : TextClickListener

    fun setOnClickedListener(listener : TextClickListener)
    {
        onClickedListener = listener
    }

}