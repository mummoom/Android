package com.mummoom.md.ui.main.community

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.internal.ContextUtils.getActivity
import com.mummoom.md.R
import java.security.AccessController.getContext

class MypageCustomDialog(context : Context) {

    private val dialog = Dialog(context)
    var birth : String = ""
    var year : String = ""
    var month : String = ""
    var day : String = ""
    lateinit var type : String
    var gender : Int=0

    // dialog 띄우는 함수
    fun MyDig()
    {
        dialog.setContentView(R.layout.custom_dialog_mypage)
        dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounding)

        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val nameEt = dialog.findViewById<EditText>(R.id.mypageDialog_name_et)
        val genderMBtn = dialog.findViewById<TextView>(R.id.mypageDialog_m_tv)
        val genderWBtn = dialog.findViewById<TextView>(R.id.mypageDialog_w_tv)

        genderMBtn.setOnClickListener {
            genderMBtn.setBackgroundResource(R.drawable.bg_modify_check_btn)
            genderWBtn.setBackgroundResource(R.drawable.bg_modify_uncheck_btn)
            genderMBtn.setTextColor(Color.parseColor("#ffffff"))
            genderWBtn.setTextColor(Color.parseColor("#000000"))
            gender=0
        }
        genderWBtn.setOnClickListener {
            genderMBtn.setBackgroundResource(R.drawable.bg_modify_uncheck_btn)
            genderWBtn.setBackgroundResource(R.drawable.bg_modify_check_btn)
            genderMBtn.setTextColor(Color.parseColor("#000000"))
            genderWBtn.setTextColor(Color.parseColor("#ffffff"))
            gender=1
        }
        typeSpinner()
        yearSpinner()
        monthSpinner()
        daySpinner()

        val doneBtn = dialog.findViewById<TextView>(R.id.mypageDialog_doneBtn_btn)
        val cancelBtn = dialog.findViewById<TextView>(R.id.mypageDialog_cancelBtn_btn)

        // 확인 버튼 눌렀을 때
        doneBtn.setOnClickListener {
            onClickedListener.onClicked(nameEt.text.toString(), type,
                gender.toString(), birth)
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
        fun onClicked(dogName : String, dogType : String, dogSex : String, dogBirth : String)
    }

    private lateinit var onClickedListener : TextClickListener

    fun setOnClickedListener(listener : TextClickListener)
    {
        onClickedListener = listener
    }

    // 스피너 함수들

    private fun typeSpinner() {

        val typeSpinner =dialog.findViewById<Spinner>(R.id.mypageDialog_type_sp)
        typeSpinner.adapter= ArrayAdapter.createFromResource(dialog.context, R.array.breed,android.R.layout.simple_spinner_dropdown_item)

        typeSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                type = typeSpinner.selectedItem.toString()

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun yearSpinner() {

        val yearSpinner =dialog.findViewById<Spinner>(R.id.mypageDialog_year_sp)
        yearSpinner.adapter= ArrayAdapter.createFromResource(dialog.context,R.array.year,android.R.layout.simple_spinner_dropdown_item)

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

        val monthSpinner =dialog.findViewById<Spinner>(R.id.mypageDialog_month_sp)
        monthSpinner.adapter= ArrayAdapter.createFromResource(dialog.context,R.array.month,android.R.layout.simple_spinner_item)

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

        val daySpinner =dialog.findViewById<Spinner>(R.id.mypageDialog_day_sp)
        daySpinner.adapter= ArrayAdapter.createFromResource(dialog.context,R.array.day,android.R.layout.simple_spinner_item)

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