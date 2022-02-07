package com.mummoom.md.ui.dogbreed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mummoom.md.R
import com.mummoom.md.databinding.ActivityDogbreedBinding
import com.mummoom.md.databinding.ActivityDoggenderBinding
import com.mummoom.md.databinding.ActivityDognameBinding
import com.mummoom.md.databinding.ActivityNicknameBinding
import com.mummoom.md.ui.BaseActivity
import com.mummoom.md.ui.dogbirth.DogbirthActivity
import com.mummoom.md.ui.doggender.DoggenderActivity
import com.mummoom.md.ui.nickname.NicknameActivity

class DogbreedActivity : BaseActivity<ActivityDogbreedBinding>(ActivityDogbreedBinding::inflate), View.OnClickListener{
    private lateinit var dogInfo : String
    private lateinit var dogType : String
    override fun initAfterBinding() {
        binding.dogbreedPreviousBtn.setOnClickListener(this)
        binding.dogbreedNextBtn.setOnClickListener(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            it.getStringExtra("dogInfo")?.let{ content->
                dogInfo=content
            }
        }

        breedSpinner()
    }
    private fun breedSpinner() {
        binding.dogbreedBreadSp.adapter=
            ArrayAdapter.createFromResource(this, R.array.breed,android.R.layout.simple_spinner_dropdown_item)

        binding.dogbreedBreadSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                dogType=binding.dogbreedBreadSp.selectedItem.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.dogbreedPreviousBtn -> startActivityWithClear(DogbirthActivity::class.java)
            binding.dogbreedNextBtn -> dogType()

        }
    }

    private fun dogType() {
        if (dogType.isEmpty()) {
            Toast.makeText(this, "강아지 종이 선택되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        dogType += ","
        dogInfo += dogType
        val intent = Intent(this,DoggenderActivity::class.java)
        intent.putExtra("dogInfo",dogInfo)
        Log.d("DOGINFO",dogInfo)
        startActivity(intent)

    }

}