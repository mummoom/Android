package com.mummoom.md.ui.dogbirth

import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.mummoom.md.R
import com.mummoom.md.databinding.ActivityDogbirthBinding
import com.mummoom.md.databinding.ActivityDoggenderBinding
import com.mummoom.md.databinding.ActivityDognameBinding
import com.mummoom.md.databinding.ActivityNicknameBinding
import com.mummoom.md.ui.BaseActivity

class DogbirthActivity : BaseActivity<ActivityDogbirthBinding>(ActivityDogbirthBinding::inflate){


    override fun initAfterBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        yearSpinner()
        monthSpinner()
        daySpinner()

    }

    private fun yearSpinner() {
        binding.birthdayinfoYearSp.adapter=ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_dropdown_item)

        binding.birthdayinfoYearSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    private fun monthSpinner() {
        binding.birthdayinfoMonthSp.adapter=ArrayAdapter.createFromResource(this,R.array.month,android.R.layout.simple_spinner_dropdown_item)

        binding.birthdayinfoMonthSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
    private fun daySpinner() {
        binding.birthdayinfoDaySp.adapter=ArrayAdapter.createFromResource(this,R.array.day,android.R.layout.simple_spinner_dropdown_item)

        binding.birthdayinfoDaySp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }





}