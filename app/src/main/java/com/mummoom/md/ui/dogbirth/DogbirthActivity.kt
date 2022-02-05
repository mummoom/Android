package com.mummoom.md.ui.dogbirth

import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mummoom.md.R
import com.mummoom.md.databinding.ActivityDogbirthBinding
import com.mummoom.md.databinding.ActivityDoggenderBinding
import com.mummoom.md.databinding.ActivityDognameBinding
import com.mummoom.md.databinding.ActivityNicknameBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogbreed.DogbreedActivity
import com.mummoom.md.ui.dogname.DognameActivity

class DogbirthActivity : BaseActivity<ActivityDogbirthBinding>(ActivityDogbirthBinding::inflate), View.OnClickListener{

    private var dogname : String = ""
    private var birth : String = ""
    private var year : String = ""
    private var month : String = ""
    private var day : String = ""

    override fun initAfterBinding() {
        binding.dogbirthPreviousBtn.setOnClickListener(this)
        binding.dogbirthNextBtn.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            it.getStringExtra("dogInfo")?.let{ content->
                dogname=content
            }
        }

        yearSpinner()
        monthSpinner()
        daySpinner()

    }

    private fun yearSpinner() {
        binding.birthdayinfoYearSp.adapter=ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_dropdown_item)

        binding.birthdayinfoYearSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                year = binding.birthdayinfoYearSp.selectedItem.toString()
                birth = year + "." + month + "." + day
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    private fun monthSpinner() {
        binding.birthdayinfoMonthSp.adapter=ArrayAdapter.createFromResource(this,R.array.month,android.R.layout.simple_spinner_dropdown_item)

        binding.birthdayinfoMonthSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                month = binding.birthdayinfoMonthSp.selectedItem.toString()
                birth = year + "." + month + "." + day
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
    private fun daySpinner() {
        binding.birthdayinfoDaySp.adapter=ArrayAdapter.createFromResource(this,R.array.day,android.R.layout.simple_spinner_dropdown_item)

        binding.birthdayinfoDaySp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                day = binding.birthdayinfoDaySp.selectedItem.toString()
                birth = year + "." + month + "." + day
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.dogbirthPreviousBtn -> startActivityWithClear(DognameActivity::class.java)
            binding.dogbirthNextBtn -> dogBirth()


        }
    }
    private fun dogBirth() {
        if (birth.isEmpty()) {
            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        val dogbirth = year+","
        val dogInfo=dogname+dogbirth
        val intent = Intent(this,DogbreedActivity::class.java)
        intent.putExtra("dogInfo",dogInfo)
        startActivity(intent)

    }


}