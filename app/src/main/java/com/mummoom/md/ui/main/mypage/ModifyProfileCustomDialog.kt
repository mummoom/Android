package com.mummoom.md.ui.main.mypage

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.mummoom.md.R

class ModifyProfileCustomDialog(context: Context) {

    private val dialog = Dialog(context)
    var birth : String = ""
    var year : String = ""
    var month : String = ""
    var day : String = ""

    // dialog 띄우는 함수
    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_modifyprofile)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val nameEt = dialog.findViewById<EditText>(R.id.modifyprofileDialog_name_et)
        val speciesEt = dialog.findViewById<EditText>(R.id.modifyprofileDialog_species_et)
        val genderEt = dialog.findViewById<EditText>(R.id.modifyprofileDialog_gender_et)

        yearSpinner()
        monthSpinner()
        daySpinner()

        val doneBtn = dialog.findViewById<TextView>(R.id.modifyprofileDialog_doneBtn_btn)
        val cancelBtn = dialog.findViewById<TextView>(R.id.modifyprofileDialog_cancelBtn_btn)

        // 확인 버튼 눌렀을 때
        doneBtn.setOnClickListener {
            onClickedListener.onClicked(nameEt.text.toString(), speciesEt.text.toString(),
                genderEt.text.toString(), birth)

            dialog.dismiss()
        }

        // 취소 버튼 눌렀을 때
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    // 확인 버튼 누를 때 입력 값들을 처리하기 위한 함수들
    interface TextClickListener{
        fun onClicked(myName : String, mySpecies : String, myGender : String, myBirth : String)
    }

    private lateinit var onClickedListener : TextClickListener

    fun setOnClickedListener(listener : TextClickListener)
    {
        onClickedListener = listener
    }

    // 스피너 함수들
    private fun yearSpinner() {

        val yearSpinner =dialog.findViewById<Spinner>(R.id.modifyprofileDialog_year_sp)
        yearSpinner.adapter= ArrayAdapter.createFromResource(dialog.context, R.array.year,android.R.layout.simple_spinner_dropdown_item)

        yearSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                year = yearSpinner.selectedItem.toString()
                birth = year + "." + month + "." + day
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun monthSpinner() {

        val monthSpinner =dialog.findViewById<Spinner>(R.id.modifyprofileDialog_month_sp)
        monthSpinner.adapter=
            ArrayAdapter.createFromResource(dialog.context, R.array.month,android.R.layout.simple_spinner_item)

        monthSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                month = monthSpinner.selectedItem.toString()
                birth = year + "." + month + "." + day
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun daySpinner() {

        val daySpinner =dialog.findViewById<Spinner>(R.id.modifyprofileDialog_day_sp)
        daySpinner.adapter=
            ArrayAdapter.createFromResource(dialog.context, R.array.day,android.R.layout.simple_spinner_item)

        daySpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                day = daySpinner.selectedItem.toString()
                birth = year + "." + month + "." + day
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

}